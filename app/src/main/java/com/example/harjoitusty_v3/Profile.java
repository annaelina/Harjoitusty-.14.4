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

//Class/activity to see own information and given ratings.
public class Profile extends OptionMenuActivity{

    private Toolbar toolbar;
    ListView listView;

    Movie_rating movie_rating = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listview);
        setSupportActionBar(toolbar);


        giveReview();
    }

    //Lists given reviews.
    public void giveReview(){
        FileManager FM = new FileManager((Movie_rating) getIntent().getSerializableExtra("key"), getApplicationContext());
        FM.writeFile();
        ArrayList<Movie_rating> arrayList_fromFile = FM.readFile();
        System.out.println(arrayList_fromFile.size());
        Collections.sort(arrayList_fromFile);
        listView(arrayList_fromFile);
    }

    public void listView(ArrayList<Movie_rating> arrayList_fromFile){
        Adapter2 adapter = new Adapter2(arrayList_fromFile, Profile.this);
        listView.setAdapter(adapter);
    }


}