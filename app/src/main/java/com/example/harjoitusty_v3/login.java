package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class login extends OptionMenuActivity{


    TextView test;
    EditText password_editText, email_editText;
    Button signIn, signUp, anonymos;
    String email, password;
    Context context;

    ArrayList<Users> usersList = new ArrayList<>();
    ArrayList<Users> arrayList_read = null;
    ArrayList<Users> arrayList_write = new ArrayList<>();


    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        email_editText = (EditText) findViewById(R.id.emailBox);
        password_editText = (EditText) findViewById(R.id.passwordBox);
        signIn = (Button) findViewById(R.id.btn_signIn);
        signUp = (Button) findViewById(R.id.btn_signUp);
        anonymos = (Button) findViewById(R.id.btn_anoUser);

    }

    public void getInformation(){
        email = email_editText.getText().toString().trim();
        password = password_editText.getText().toString().trim();


        //usersList.add(new Users(email, password));

        System.out.println(email);
    }

    public  void singIn(View v){
        signIn.setOnClickListener(new View.OnClickListener() {

            
            @Override
            public void onClick(View view) {
                getInformation();

                startActivity(new Intent(login.this, MainActivity.class));
            }
        });

    }

    public  void singUp(View v){
        getInformation();
        usersList.add(new Users(email, password));
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });

    }

    public  void anonymos(View v){
        anonymos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });

    }

}