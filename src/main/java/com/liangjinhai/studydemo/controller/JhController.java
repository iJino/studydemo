package com.liangjinhai.studydemo.controller;

import com.liangjinhai.studydemo.Lottery.JhService;
import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;
import com.liangjinhai.studydemo.Lottery.service.LotteryCompareService;
import com.liangjinhai.studydemo.common.StaticProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jin
 * @Title: JhController
 * @date 19-1-21下午9:32
 */
@Controller
@RequestMapping("/jh")
public class JhController {

    @Resource
    private JhService jhService;
    @Resource
    private JedisCluster jedisCluster;
    @Resource
    private LotteryCompareService lotteryCompareService;

    @RequestMapping("/{type}/query")
    public String lotteryIndex(@PathVariable("type") String type, Model model){
        String result = jhService.queryLottery(type);
        Long end = jedisCluster.llen(StaticProperties.DOUBLE_COLOR_BALL);
        List<String> forecast = jedisCluster.lrange(StaticProperties.DOUBLE_COLOR_BALL,0,end-1);
        model.addAttribute("forecast",forecast);
        model.addAttribute("result",result);
        return "/lottery/index";
    }

    @RequestMapping("/lottery/compate")
    public String lotteryCompare(Model model){
        List<LotteryCompare> compares = lotteryCompareService.findAll();
        model.addAttribute("compares",compares);
        return "/lottery/compare";
    }
}
