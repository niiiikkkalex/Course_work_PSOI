package com.example.kursach_client1.models;

import java.io.Serializable;

public class Authorization  implements Serializable {
    private String login;
    private String password;

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

}
