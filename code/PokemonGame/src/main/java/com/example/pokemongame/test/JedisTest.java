package com.example.pokemongame.test;

import com.example.pokemongame.util.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("hello","world");
        jedis.close();
    }
}
