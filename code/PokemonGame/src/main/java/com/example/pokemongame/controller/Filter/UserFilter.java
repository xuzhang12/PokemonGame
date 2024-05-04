package com.example.pokemongame.controller.Filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 这里是用户权限过滤器，当用户登录时，session域会设置一个User属性，用户退出时主动销毁，或者session会话结束时销毁
 * 通过该属性的有无来判断用户是否有权限访问相关的数据
 */

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        if(user!=null)
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
