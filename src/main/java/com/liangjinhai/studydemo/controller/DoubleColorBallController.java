package com.liangjinhai.studydemo.controller;

import com.liangjinhai.studydemo.common.StaticProperties;
import com.liangjinhai.studydemo.domin.DoubleColorBall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/DoubleColorBall")
public class DoubleColorBallController {

    @Resource
    private JedisCluster jedisCluster;

    public String index(){
        List<DoubleColorBall> prediction = new ArrayList<>();
        jedisCluster.get(StaticProperties.DOUBLE_COLOR_BALL);
        return "doubleColorBall/index";
    }
}
