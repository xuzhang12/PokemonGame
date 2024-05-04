package com.example.pokemongame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 这里是用于密码加密的工具类
 */
public class MD5Utils {
    //加密方式
    public static String md5Password(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[]result=digest.digest(password.getBytes());
            StringBuffer buffer=new StringBuffer();
            //把每一个byte跟0xff做与运算；
            for (byte b:result){
                int number=b&0xff;//加盐
                String str=Integer.toHexString(number);
                if(str.length()==1)
                {
                    buffer.append("b");
                }
                buffer.append(str);
            }
            //返回标准的MD5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
