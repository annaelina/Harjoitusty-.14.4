package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Profile extends OptionMenuActivity{

    private Toolbar toolbar;
    ListView listView;
    Movie_rating movie_rating;
    ArrayList<Movie_rating> arrayList;
    ArrayAdapter<Movie_rating> adapter = null;
    Context context = Profile.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listview);
        setSupportActionBar(toolbar);

        movie_rating = (Movie_rating) getIntent().getSerializableExtra("key");
        writeFile(movie_rating);
        readFile();

    }

    public void writeFile(Movie_rating movie_rating){
        try{
            FileOutputStream fos = new FileOutputStream(context.getFilesDir() + "movies_rating.txt", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(movie_rating);
            oos.close();
            System.out.println("Kirjoitettu");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile(){
        try{
            FileInputStream fis = new FileInputStream(context.getFilesDir() + "movies_rating.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //tässä yritetään lukea tiedosto "movies_rating.txt" jossa on arvosteluolioita ja laittaa oliot arraylistiin
            //Onko seuraavat 3 riviä oikein? Mitä tulisi muuttaa?

            Object line;
            while((line = ois.readObject()) != null){
                arrayList.add((Movie_rating) ois.readObject());
            }

            //arrayList = (ArrayList<Movie_rating>) ois.readObject();
            ois.close();
            System.out.println("Luettu");

            System.out.println(arrayList.get(0).name);

            Collections.sort(arrayList);

            for(Movie_rating i : arrayList){
                System.out.println(i.toString());
            }

            listView(arrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void listView(ArrayList<Movie_rating> arrayList){
        adapter = new ArrayAdapter<Movie_rating>(this, android.R.layout.simple_spinner_item, arrayList);
        listView.setAdapter(adapter);

    }


}