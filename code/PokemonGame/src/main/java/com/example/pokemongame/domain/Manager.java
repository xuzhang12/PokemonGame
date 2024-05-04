package com.example.pokemongame.domain;
/**
 * 这是管理员类
 */
public class Manager {
    private String name;
    private String password;
    private String email="";
    private String phone="";

    public Manager(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        if(email!=null) this.email = email;
        if(phone!=null)this.phone = phone;
    }
    public Manager(){}

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
        if(email!=null) this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone!=null)this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
