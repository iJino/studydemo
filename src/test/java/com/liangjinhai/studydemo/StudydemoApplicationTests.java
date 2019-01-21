package com.liangjinhai.studydemo;

import com.liangjinhai.studydemo.Lottery.JhService;
import com.liangjinhai.studydemo.common.StaticProperties;
import com.liangjinhai.studydemo.common.util.OKHttpUtil;
import com.liangjinhai.studydemo.sortDemo.DoubleColorBallSort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudydemoApplicationTests {

    @Resource
    private JhService jhService;
    @Resource
    private JedisCluster jedisCluster;

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
}

