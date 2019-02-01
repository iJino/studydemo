package com.liangjinhai.studydemo.controller;

import com.liangjinhai.studydemo.common.result.BaseResult;
import com.liangjinhai.studydemo.webSocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@RequestMapping("/checkcenter")
@Controller
public class CheckCenterController {

    @GetMapping("/socket/{userId}")
    public String socket(@PathVariable("userId")String userId, Model model){
        model.addAttribute("userId",userId);
        return "/websocket/socketTest";
    }

    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public BaseResult pushToWeb(@PathVariable("cid")String cid,String message){
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResult.failed(cid+"#"+e.getMessage());
        }
        return BaseResult.success(cid);
    }
}

