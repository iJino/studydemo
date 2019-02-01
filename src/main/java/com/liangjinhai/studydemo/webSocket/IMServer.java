package com.liangjinhai.studydemo.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liangjinhai.studydemo.common.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liangJinHai
 * @Date: 2019/2/1 15:36
 * @Description: 将接受信息的客户端改为ConcurrentHashMap既保证线程安全，有可以对指定用户发送信息
 */
@Component
@ServerEndpoint("/im/{userId}")
public class IMServer {

    private static final Logger log = LoggerFactory.getLogger(IMServer.class);
    //静态变量，用于记录当前连接数，应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //新：使用map对象，便于根据userId来获取对应的WebSocket
    private static ConcurrentHashMap<String,IMServer> websocketList = new ConcurrentHashMap<>(); //接收sid private String userId="";
    //接受userId
    private String userId;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        websocketList.put(userId,this);             //加入到websocket列表中
        log.info("websocketList->"+JSON.toJSONString(websocketList));
        addOnlineCount();                   //在线数+1
        log.info("有新的窗口开始监听：" + userId + ", 当前在线人数为：" + getOnlineCount());
        this.userId = userId;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        if(websocketList.get(this.userId) != null){
            websocketList.remove(this.userId);
            subOnlineCount();                    //在线数-1
            log.info("有一连接已关闭！当前在线人数为：" + getOnlineCount());
        }
        //webSocketSet.remove(this);        //从列表中删除

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + userId + "的信息：" + message);
        if(StringUtils.isNotEmpty(message)){
            JSONArray list = JSONArray.parseArray(message);
            for(int i=0;i<list.size();i++){
                try {
                    JSONObject object = list.getJSONObject(i);
                    String toUserId = object.getString("toUserId");
                    String contentText = object.getString("contentText");
                    object.put("formUserId",this.userId);
                    if(StringUtils.isNotBlank(toUserId)&&StringUtils.isNotBlank(contentText)){
                        IMServer socketx = websocketList.get(toUserId);
                        if(socketx!=null){
                            System.out.println(object);
                            socketx.sendMessage(JsonUtil.object2Json(object));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }
}
