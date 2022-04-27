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

public class Profile extends OptionMenuActivity{

    private Toolbar toolbar;
    ListView listView;
    //rrayList<Movie_rating> arrayList_toFile = new ArrayList<>();
    ArrayList<Movie_rating> arrayList_fromFile = new ArrayList<>();
    ArrayAdapter<Movie_rating> adapter = null;
    String a = null;
    Movie_rating movie_rating = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listview);
        setSupportActionBar(toolbar);


        FileManager FM = new FileManager((Movie_rating) getIntent().getSerializableExtra("key"), getApplicationContext());
        FM.writeFile();
        arrayList_fromFile = FM.readFile();
        //Collections.sort(arrayList_fromFile);
        listView(arrayList_fromFile);



        /*a = (String) getIntent().getStringExtra("key1");
        if ( a == null){
            FileManager FM = new FileManager(null, getApplicationContext());
            arrayList_fromFile = FM.readFile(arrayList_fromFile);
            if ( arrayList_fromFile != null){
                listView(arrayList_fromFile);
            }
        }
        else {
            giveReview();
        }*/

    }

    /*public void giveReview(){
        //movie_rating = (Movie_rating) getIntent().getSerializableExtra("key");
        FileManager FM = new FileManager((Movie_rating) getIntent().getSerializableExtra("key"), getApplicationContext());
        FM.writeFile();
        arrayList_fromFile = FM.readFile(arrayList_fromFile);
        //Collections.sort(arrayList_fromFile);
        listView(arrayList_fromFile);
    }*/



    public void listView(ArrayList<Movie_rating> arrayList_fromFile){
        adapter = new ArrayAdapter<Movie_rating>(this, android.R.layout.simple_spinner_item, arrayList_fromFile);
        listView.setAdapter(adapter);

    }


}