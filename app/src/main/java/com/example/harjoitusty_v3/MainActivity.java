package com.example.harjoitusty_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends OptionMenuActivity {

    Button button1, button2;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }



    public void searchMovie(View v){

    }
    public void listView(View v){

    }
    public void rankMovies(View v){

    }


    /*Bring arraylist from "Read xml" class*/
    public void readXML(View v){
    }
}