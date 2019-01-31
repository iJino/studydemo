package com.liangjinhai.studydemo;

import com.liangjinhai.studydemo.Lottery.JhService;
import com.liangjinhai.studydemo.Lottery.domin.QueryRQ;
import com.liangjinhai.studydemo.common.StaticProperties;
import com.liangjinhai.studydemo.common.util.JsonUtil;
import com.liangjinhai.studydemo.common.util.OKHttpUtil;
import com.liangjinhai.studydemo.sortDemo.DoubleColorBallSort;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudydemoApplicationTests {

    @Resource
    private JhService jhService;
    @Resource
    private JedisCluster jedisCluster;
    private static FastDateFormat yMd = FastDateFormat.getInstance("yyyy-MM-dd");

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        q:
        for(int i = 0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==3){
                    break q;
                }
                System.out.println(+i+"-"+j);
            }
            System.out.println(""+i);
        }
    }

    @Test
    public void test2(){
        List<String> doubleball = DoubleColorBallSort.getNumber();
        jedisCluster.del(StaticProperties.DOUBLE_COLOR_BALL);
        String[] balls = doubleball.toArray(new String[doubleball.size()]);
        jedisCluster.lpush(StaticProperties.DOUBLE_COLOR_BALL,balls);
        System.out.println(jedisCluster.get(StaticProperties.DOUBLE_COLOR_BALL));
        System.out.println(doubleball);
    }

    @Test
    public void test3(){
        String result = OKHttpUtil.httpGet("http://f.apiplus.net/ssq.json");
        System.out.println(result);
    }

    @Test
    public void test4(){
        String result = jhService.queryLottery("ssq");
        System.out.println(result);
    }

    @Test
    public void test5(){
        Long end = jedisCluster.llen(StaticProperties.DOUBLE_COLOR_BALL);
        List<String> forecast = jedisCluster.lrange(StaticProperties.DOUBLE_COLOR_BALL,0,end-1);
        String result = jhService.queryLottery("ssq");
        if(result != null){
            QueryRQ rq = JsonUtil.json2Object(result,QueryRQ.class);
            String res = rq.getResult().getLottery_res();
            List<String> official = Arrays.asList(res.split(","));
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
                    }
                }
            }
        }
    }

    @Test
    public void test6() throws ParseException {
        Date d = yMd.parse("2019-03-01");
        String start = "";
        String end = "";
        Calendar today = Calendar.getInstance();
        today.setTime(d);
        today.add(Calendar.MONTH, -1);
        Date startDate = today.getTime();
        start = yMd.format(startDate);
        today.setTime(d);
        today.add(Calendar.DATE,-1);
        Date endDate = today.getTime();
        end = yMd.format(endDate);
        System.out.println(start+"***1***"+end);
        List<String> t = Arrays.asList(start.split("-"));
        start = t.get(0)+"-"+t.get(1)+"-15";
        System.out.println(start+"***2***"+end);
        today.setTime(d);
        today.add(Calendar.DATE,-1);
        Date endDate1 = today.getTime();
        end = yMd.format(endDate1);
        List<String> t1 = Arrays.asList(start.split("-"));
        start = t.get(0)+"-"+t.get(1)+"-01";
        System.out.println(start+"***3***"+end);
        today.setTime(d);
        today.add(Calendar.DATE,-1);
        Date endDate2 = today.getTime();
        end = yMd.format(endDate2);
        today.setTime(d);
        today.add(Calendar.DATE,-7);
        Date startDate2 = today.getTime();
        start = yMd.format(startDate2);
        System.out.println(start+"***4***"+end);
    }
}

