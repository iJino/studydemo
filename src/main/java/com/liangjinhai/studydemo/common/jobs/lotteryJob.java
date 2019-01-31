package com.liangjinhai.studydemo.common.jobs;

import com.liangjinhai.studydemo.Lottery.JhServiceImpl;
import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;
import com.liangjinhai.studydemo.Lottery.domin.QueryRQ;
import com.liangjinhai.studydemo.Lottery.service.LotteryCompareService;
import com.liangjinhai.studydemo.common.StaticProperties;
import com.liangjinhai.studydemo.common.util.JsonUtil;
import com.liangjinhai.studydemo.sortDemo.DoubleColorBallSort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 双色球预测比例
 */
@Component
public class lotteryJob {

    @Resource
    private JhServiceImpl jhService;
    @Resource
    private JedisCluster jedisCluster;
    @Resource
    private LotteryCompareService lotteryCompareService;

//    public final static long ONE_Minute = 1000;
//
//    @Scheduled(fixedRate=ONE_Minute)
    public void lotteryCompare(){
        Long end = jedisCluster.llen(StaticProperties.DOUBLE_COLOR_BALL);
        List<String> forecast = jedisCluster.lrange(StaticProperties.DOUBLE_COLOR_BALL,0,end-1);
        String result = jhService.queryLottery("ssq");
        if(result != null){
            QueryRQ rq = JsonUtil.json2Object(result,QueryRQ.class);
            if(lotteryCompareService.exist(rq.getResult().getLottery_no())){
                String res = rq.getResult().getLottery_res();
                List<String> official = Arrays.asList(res.split(","));
                String number = "";
                if(forecast.size()>0){
                    double probability = 0.00;
                    for(String f : forecast){
                        int in = 0;
                        List<String> data = Arrays.asList(f.split(","));
                        for(String b : data){
                            if(official.contains(b)){
                                in = in+1;
                            }
                        }
                        double proportion = in/7.0;
                        if(proportion == 1.0){
                            System.out.println(data);
                        }
                        if(proportion > probability || probability == 0.00){
                            probability = proportion;
                            number = f;
                        }
                    }
                    LotteryCompare lotteryCompare = new LotteryCompare();
                    lotteryCompare.setCreateTime(new Date());
                    lotteryCompare.setResult(res);
                    lotteryCompare.setNumbers(number);
                    lotteryCompare.setProbability(probability);
                    lotteryCompare.setTime(rq.getResult().getLottery_no());
                    lotteryCompareService.save(lotteryCompare);
                    jedisCluster.del(StaticProperties.DOUBLE_COLOR_BALL);
                    List<String> doubleball = DoubleColorBallSort.getNumber();
                    String[] balls = doubleball.toArray(new String[doubleball.size()]);
                    jedisCluster.lpush(StaticProperties.DOUBLE_COLOR_BALL,balls);
                }else{
                    jedisCluster.del(StaticProperties.DOUBLE_COLOR_BALL);
                    List<String> doubleball = DoubleColorBallSort.getNumber();
                    String[] balls = doubleball.toArray(new String[doubleball.size()]);
                    jedisCluster.lpush(StaticProperties.DOUBLE_COLOR_BALL,balls);
                }
            }
        }
    }
}
