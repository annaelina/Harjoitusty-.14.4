package com.example.harjoitusty_v3;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {

    Movie_rating movie_rating;
    Context context;


    ArrayList<Movie_rating> ratedMovies = new ArrayList<>();
    ArrayList<Movie_rating> arrayList_read = null;
    ArrayList<Movie_rating> arrayList_write = new ArrayList<>();



    public FileManager(Movie_rating movie_rating, Context context){
        this.movie_rating = movie_rating;
        this.context = context;

    }

    public void writeFile(){
        try{
            //File file = new File(context.getFilesDir()+ "movie_rating.txt");

            /*if(file.exists()){
                arrayList_write = readFile(arrayList_read);
                for (Movie_rating mov: arrayList_write){
                    ratedMovies.add(mov);
                }
            }*/

            arrayList_write.add(movie_rating);

            FileOutputStream fos = new FileOutputStream(context.getFilesDir()+ "movie_rating.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList_write);
            oos.close();
            System.out.println("Kirjoitettu");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public ArrayList<Movie_rating> readFile(){
        try{
            FileInputStream fis = new FileInputStream(context.getFilesDir()+ "movies_rating.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //tässä yritetään lukea tiedosto "movies_rating.txt" jossa on arvosteluolioita ja laittaa oliot arraylistiin
            //Onko seuraavat 3 riviä oikein? Mitä tulisi muuttaa?

            arrayList_read = (ArrayList<Movie_rating>) ois.readObject();

            //arrayList = (ArrayList<Movie_rating>) ois.readObject();
            ois.close();
            System.out.println("Luettu");
            ///data/data/com.example.harjoitusty_v3/filesmovies_rating.txt

            /*
            if ( arrayList.size() > 0 ) {
                System.out.println(arrayList.get(0).name);
            }

            Collections.sort(arrayList);

            for(Movie_rating i : arrayList){
                System.out.println(i.toString());
            } */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList_read;

    }
}
