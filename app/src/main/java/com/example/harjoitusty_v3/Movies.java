package com.example.harjoitusty_v3;

import java.util.Date;

public class Movies {
    String name;
    int id;
    String date;

    public Movies(){

    }

    public Movies(String name, int id, String date){
        this.name = name;
        this.id = id;
        this.date = date;
    }
    public String getName(){
        return name;
    }

    public int getID(){
        return id;
    }

    public String getDate(){
        return  date;
    }
}
