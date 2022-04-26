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
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {

    Movie_rating movie_rating;
    Context context;
    String file_name;


    ArrayList<Movie_rating> ratedMovies = new ArrayList<>();
    ArrayList<Movie_rating> arrayList_read = null;
    ArrayList<Movie_rating> arrayList_write = new ArrayList<>();



    public FileManager(Movie_rating movie_rating, Context context){
        this.movie_rating = movie_rating;
        this.context = context;

    }

    public void writeFile(){
        try{
            File file = new File(context.getFilesDir()+ "movie_rating.txt");

            if(file.exists()){
                arrayList_write = readFile(arrayList_read);
                for (Movie_rating mov: arrayList_write){
                    ratedMovies.add(mov);
                }
            }

            arrayList_write.add(movie_rating);

            //FileOutputStream fos = new FileOutputStream(context.getFilesDir() + "movie_rating.txt");
            FileOutputStream fos = context.openFileOutput("movie_rating.txt", Context.MODE_PRIVATE);
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

    public ArrayList<Movie_rating> readFile(ArrayList arrayList_read){
        try{
            //FileInputStream fis = new FileInputStream(context.getFilesDir()+ "movies_rating.txt");;
            FileInputStream fis = context.openFileInput("movie_rating.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList_read= ((ArrayList<Movie_rating>) ois.readObject());
            //arrayList_read = (ArrayList<Movie_rating>) ois.readObject();
            ois.close();
            System.out.println("Luettu");


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
