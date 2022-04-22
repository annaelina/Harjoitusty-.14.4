package com.example.harjoitusty_v3;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {

    Movie_rating movie_rating = null;

    ArrayList<Movie_rating> ratedMovies = new ArrayList<>();
    ArrayList<Movie_rating> arrayList = new ArrayList<>();


    /*movie_rating = (Movie_rating) getIntent().getSerializableExtra("key");
    writeFile(movie_rating);
    readFile();*/

    public FileManager(Movie_rating movie_rating){
        this.movie_rating = movie_rating;

    }

    public void writeFile(){
        try{
            File file = new File("movie_rating.txt");
            if(file.exists()){
                arrayList = readFile(arrayList);
                for ( Movie_rating mov: arrayList){
                    ratedMovies.add(mov);
                }
            }

            ratedMovies.add(movie_rating);

            FileOutputStream fos = new FileOutputStream("movies_rating.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ratedMovies);
            oos.close();
            System.out.println("Kirjoitettu");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Movie_rating> readFile(ArrayList<Movie_rating> arrayList){
        try{
            FileInputStream fis = new FileInputStream("movies_rating.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //tässä yritetään lukea tiedosto "movies_rating.txt" jossa on arvosteluolioita ja laittaa oliot arraylistiin
            //Onko seuraavat 3 riviä oikein? Mitä tulisi muuttaa?

            arrayList = (ArrayList<Movie_rating>) ois.readObject();

            //arrayList = (ArrayList<Movie_rating>) ois.readObject();
            ois.close();
            System.out.println("Luettu");

            if ( arrayList.size() > 0 ) {
                System.out.println(arrayList.get(0).name);
            }

            Collections.sort(arrayList);

            for(Movie_rating i : arrayList){
                System.out.println(i.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList;

    }
}
