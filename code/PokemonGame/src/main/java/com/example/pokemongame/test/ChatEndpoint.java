package com.example.pokemongame.test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Message;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.UserServiceimpl;
import com.example.pokemongame.util.MessageUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket端点类，但是用的比较少，当时写的时候也是在test里面写，所以就继续放在这里面了，因为也不知道要放在哪里哈哈哈
 */
@ServerEndpoint(value = "/pages/user/announcement.jsp",configurator=CustomHttpSessionConfigurator.class)
public class ChatEndpoint{
    private static final Map<Integer,Session> connection=new ConcurrentHashMap<>();
    private HttpSession httpSession;
    private User user;

    /**
     * 建立websocket连接后，被调用，发布公告给访问的用户
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        //1.将session保存
        this.httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        user= (User) httpSession.getAttribute("user");
        connection.put(user.getId(),session);
        System.out.println("建立与"+user.getName()+"的连接");
        //2.公告消息，发送给所有访问登录的用户
        UserService userService=new UserServiceimpl();
        List<Announcement> announcements = userService.queryAnnouncement();
        Gson gson=new Gson();
        String dayAndMessage = gson.toJson(announcements);
        broadcastToAllUsers(dayAndMessage);
    }

    /**
     * 断开websocket时自动调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("断开"+user.getName()+"的连接");
        connection.remove(user.getId());
    }


    /**
     * 收到浏览器发送的消息时执行
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message){
        System.out.println("收到用户" + user.getName() + "的消息:" + message);
        Message msg=JSON.parseObject(message, Message.class);
        int toUserId = msg.getToUserId();
        String mess = msg.getMessage();
        Session session = connection.get(toUserId);
        try {
            session.getBasicRemote().sendText(MessageUtils.getMessage(false,user.getName(),mess));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void broadcastToAllUsers(String dayAndMessage){
        Set<Map.Entry<Integer, Session>> entries = connection.entrySet();
        for (Map.Entry<Integer, Session> entry : entries) {
            Session session = entry.getValue();
            session.getAsyncRemote().sendText(dayAndMessage);
        }
    }
}
