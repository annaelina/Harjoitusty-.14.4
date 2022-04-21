package com.example.harjoitusty_v3;

import java.io.Serializable;

public class Movie_rating implements Serializable, Comparable<Movie_rating> {

    String name;
    String comment;
    Float rating;

    public Movie_rating(Float rating, String name, String comment){
        this.name = name;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString(){
        return this.name;
    }

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
