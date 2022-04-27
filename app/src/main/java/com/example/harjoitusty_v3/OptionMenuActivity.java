package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//Class/activity for menu.
public class OptionMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Profile:
                seeProfile();
                return true;
            case R.id.Mainpage:
                seeMainpage();
                return true;
            case R.id.Findreview:
                seeFindreview();
                return true;
            case R.id.login:
                seeLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void seeProfile(){
        Intent intent = new Intent(OptionMenuActivity.this, Profile.class);
        startActivity(intent);
    }
    public void seeMainpage(){
        Intent intent = new Intent(OptionMenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void seeFindreview(){
        Intent intent = new Intent(OptionMenuActivity.this, Find_review.class);
        startActivity(intent);
    }
    public void seeLogin(){
        Intent intent = new Intent(OptionMenuActivity.this, login.class);
        startActivity(intent);
    }
}
