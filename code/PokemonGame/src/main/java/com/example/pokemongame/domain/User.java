package com.example.pokemongame.domain;

import java.util.Date;

/**
 * 这是用户类，online为 1 表示在线，0 表示离线，open为 1 表示公开，0 表示不公开
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int online=1;
    private String nickname="";//昵称
    private String signature="";//签名
    private Date birthday=new Date();//生日，默认为当前日期
    private int open=1;

    public User(int id, String name, String password, String email, int online, String nickname, String signature, Date birthday, int open) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.online = online;
        this.nickname = nickname;
        this.signature = signature;
        this.birthday = birthday;
        this.open=open;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int isonline() {
        return online;
    }

    public void setonline(int online) {
        this.online = online;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", online=" + online +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", birthday=" + birthday +
                ", open=" + open +
                '}';
    }
}
