package com.example.pokemongame.util;


/**
 * 这里是与升级有关的工具类，但是其静态方法已经基本被替代，静态成员变量还在使用，此处保留其方法用于描述与升级有关的做法
 */
public class UpgradeUtils {
    public static double power=3;
    public static double[] baseExp={2,3,4};//不同稀有度的宠物对应的基础经验值：普通-->>2，稀有-->>3，史诗-->>4
    public static int[] baseAttribute={2,4,6};//不同稀有度的宠物对应的基础属性值：普通-->>2，稀有-->>4，史诗-->>6
    public static double multiplier=1.5;
}

