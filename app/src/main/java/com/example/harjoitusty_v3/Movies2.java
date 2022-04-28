package com.example.harjoitusty_v3;

import java.io.Serializable;

public class Movies2 implements Serializable {

    String name;
    int id;

    public Movies2(String name, int ID) {
        this.name = name;
        this.id = ID;
    }

    public String getName(){
        return name;
    }


    @Override
    public String toString() {
        return name;
    }

}
