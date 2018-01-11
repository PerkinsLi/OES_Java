package com.perkins.oes.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8437031542917229148L;
    private int user_id;
    private String user_name;
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name
                + ", password=" + password + "]";
    }

}
