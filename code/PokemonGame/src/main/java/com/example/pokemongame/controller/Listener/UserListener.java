package com.example.pokemongame.controller.Listener;

import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.UserServiceimpl;
import com.example.pokemongame.util.DBUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserListener implements HttpSessionListener {
    UserService userService = new UserServiceimpl();

    /**
     * 设置session会话时长
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setMaxInactiveInterval(20 * 60);
    }

    /**
     * session会话结束时如果有用户未主动退出，通过监听器更新该用户状态为离线状态
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setonline(0);
            try {
                userService.updateUserInfo(user);
                DBUtils.commitAndClose();
            } catch (Exception e) {
                DBUtils.rollbackAndClose();
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
