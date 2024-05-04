package com.example.pokemongame.controller.Filter;

import com.example.pokemongame.util.DBUtils;
import javax.servlet.*;
import java.io.IOException;

/**
 * 这里是借鉴B站教学视频的做法，对所有的资源加上filter过滤器，从而管理与数据库有关的事务（主要是各种service内的dao操作）
 * 要么全部成功并提交，要么全部失败并回滚
 */

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            DBUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            DBUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给tomcat管理展示
        }
    }

    @Override
    public void destroy() {

    }
}
