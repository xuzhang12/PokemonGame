package com.example.pokemongame.util;

import com.alibaba.fastjson.JSON;
import com.example.pokemongame.domain.ResultMessage;

/**
 * 消息工具类，websocket不会用，该工具也用得少
 */
public class MessageUtils {
    public static String getMessage(boolean isSystemMessage,String fromName,Object message){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setSystem(isSystemMessage);
        resultMessage.setMessage(message);
        if (fromName!=null){
            resultMessage.setFromName(fromName);
        }
        return JSON.toJSONString(resultMessage);
    }
}
