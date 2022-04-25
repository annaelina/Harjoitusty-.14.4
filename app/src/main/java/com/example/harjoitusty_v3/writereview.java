package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class writereview extends AppCompatActivity {

    String name, comment;
    //Context context = writereview.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writereview);

        //elokuvan nimen hakeminen
        name = getIntent().getStringExtra("key");
        System.out.println(name);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submit_button = (Button) findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float rating = (Float) ratingBar.getRating();
                //System.out.println(rating);
                Movie_rating movie_rating = new Movie_rating(new Float(rating), name, comment);
                //writeFile(movie_rating);

                FileManager FM = new FileManager(movie_rating);
                FM.writeFile();

                Intent intent = new Intent(writereview.this, Profile.class);
                //intent.putExtra("key", movie_rating);
                startActivity(intent);

            }
        });

    }




}