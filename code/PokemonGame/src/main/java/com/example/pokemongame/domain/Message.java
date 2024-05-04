package com.example.pokemongame.domain;

/**
 * 消息类，后面基本没有使用，因为没有用springboot不会实现websocket，没有办法邀请其他用户对战和对话
 */
public class Message {
    private int toUserId;
    private String message;

    public Message() {
    }

    public Message(int toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "toUserId='" + toUserId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
