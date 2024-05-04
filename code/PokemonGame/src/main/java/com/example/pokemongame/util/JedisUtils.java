package com.example.pokemongame.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这里是redis连接的工具类
 */
public class JedisUtils {
    private static JedisPool jedisPool;
    public static String signKey="signIn_bitmap:";//设置key的前缀
    static {
        InputStream inputStream = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        jedisPool=new JedisPool(config,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
