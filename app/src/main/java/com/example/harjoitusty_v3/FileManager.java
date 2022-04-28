package com.example.harjoitusty_v3;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

    Movie_rating movie_rating;
    Context context;



    public FileManager(Movie_rating movie_rating, Context context){
        this.movie_rating = movie_rating;
        this.context = context;
    }

    public void writeFile(){
        File file = new File(context.getFilesDir(), "movie_rating");
        ArrayList<Movie_rating> arrayList_write = new ArrayList<>();
        try{
            arrayList_write = readFile();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            if( movie_rating != null){
                arrayList_write.add(movie_rating);
            }
            else{
                System.out.println("Movie rating was null");
            }

            FileOutputStream fos = context.openFileOutput("movie_rating", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList_write);
            oos.close();
            System.out.println(arrayList_write.size());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie_rating> readFile(){
        ArrayList<Movie_rating> arrayList_read = new ArrayList<>();
        try{
            FileInputStream fis = context.openFileInput("movie_rating");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList_read = ((ArrayList<Movie_rating>) ois.readObject());
            System.out.println(arrayList_read.size());
            ois.close();
            System.out.println("Luettu");
            return arrayList_read;


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
