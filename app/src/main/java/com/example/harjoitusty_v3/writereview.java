package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//Class/activity to give reviews to a chosen movie.
public class writereview extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writereview);

        //Gets the name of the movie from previous activity.
        name = getIntent().getStringExtra("key");
        TextView txt = (TextView) findViewById(R.id.textView2);
        txt.setText(name);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submit_button = (Button) findViewById(R.id.submit_button);

        //Changes to Profile activity and takes movie_rating object.
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie_rating movie_rating = new Movie_rating(new Float(ratingBar.getRating()), name);
                Intent intent = new Intent(writereview.this, Profile.class);
                intent.putExtra("key", movie_rating);
                startActivity(intent);
            }
        });
    }
}