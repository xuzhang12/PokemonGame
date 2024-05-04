package com.example.pokemongame.domain;

/**
 * 该类不使用了
 */
public class ResultMessage {
    private boolean isSystem;
    private Object message;
    private String fromName=null;

    public ResultMessage() {
    }

    public ResultMessage(boolean isSystem, Object message, String fromName) {
        this.isSystem = isSystem;
        this.message = message;
        this.fromName = fromName;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "isSystem=" + isSystem +
                ", message='" + message + '\'' +
                ", fromName='" + fromName + '\'' +
                '}';
    }
}
