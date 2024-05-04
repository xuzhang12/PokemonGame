package com.example.pokemongame.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 这是用于将参数包装成 Bean 对象的工具类
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value,T bean)
    {
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
