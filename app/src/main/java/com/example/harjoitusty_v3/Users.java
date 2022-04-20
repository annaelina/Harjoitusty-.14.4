package com.example.harjoitusty_v3;

import android.widget.TextView;

public class Users {

    String email, password;

    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
