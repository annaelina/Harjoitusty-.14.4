package com.example.harjoitusty_v3;

import android.widget.ArrayAdapter;

import java.io.Serializable;

public class Movie_rating implements Serializable, Comparable<Movie_rating> {

    String name;
    Float rating;

    public Movie_rating(Float rating, String name){
        this.name = name;
        this.rating = rating;
    }

    public String getName(){
        return name;
    }

    public Float getRating(){
        return  rating;
    }

    @Override
    public String toString(){
        return name;
    }


    //Puts rated movies in order.
    @Override
    public int compareTo(Movie_rating movie_rating) {
        if (rating.floatValue() < movie_rating.rating.floatValue()) {
            return 1;
        }
        else if (rating.floatValue() >  movie_rating.rating.floatValue()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
