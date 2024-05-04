package com.example.pokemongame.controller.Filter;

import com.example.pokemongame.domain.Manager;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 这里是对管理员权限的过滤器
 */

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 管理员登录时会给session设置manager属性，里面存放manager的相关信息，管理员退出时销毁该属性，通过有无该属性进行拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Manager manager = (Manager)session.getAttribute("manager");
        if(manager!=null)
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            httpServletRequest.getRequestDispatcher("/pages/manager/login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
