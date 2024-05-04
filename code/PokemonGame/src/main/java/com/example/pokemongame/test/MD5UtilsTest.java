package com.example.pokemongame.test;

import com.example.pokemongame.util.MD5Utils;

public class MD5UtilsTest {
    public static void main(String[] args) {
        String pwd1= MD5Utils.md5Password("Zxy040223");
        System.out.println(pwd1);
    }
}
