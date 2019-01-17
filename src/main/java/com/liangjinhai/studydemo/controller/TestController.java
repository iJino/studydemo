package com.liangjinhai.studydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test(String name, Model model){
        model.addAttribute("test","卢镇安我是你爸爸");
        return "儿子，儿子"+ name +", 我是你爸爸";
    }

    @RequestMapping("/testView")
    public ModelAndView testView(){
        ModelAndView mv = new ModelAndView("test1");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(String name){
        return "sb了吧，卢镇安";
    }
}
