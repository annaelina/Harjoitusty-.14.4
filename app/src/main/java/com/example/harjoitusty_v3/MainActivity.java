package com.example.harjoitusty_v3;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends OptionMenuActivity {

    Button button1, button2;
    private Toolbar toolbar;
    Movies schedule = null;
    ArrayList<Movies> arrayList = new ArrayList<>();
    ListView listView;
    CalendarView calendarView;
    int day = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //getApplicationContext().openFileOutput();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        button1 = (Button) findViewById(R.id.button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listview);
        //calendarView = (CalendarView) findViewById(R.id.calendarView);
        setSupportActionBar(toolbar);






        readXML();
        //calendarView();
        searchMovie();
;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void searchMovie(){
        SearchView searchView = (SearchView) findViewById(R.id.searchMovie);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Movies> filteredMovies = new ArrayList<>();
                for (Movies movies: arrayList){
                    if (movies.getName().toLowerCase().contains(s.toLowerCase())){
                        filteredMovies.add(movies);
                    }
                }
                Adapter adapter = new Adapter(filteredMovies, MainActivity.this);
                listView.setAdapter(adapter);



                return false;
            }
        });


    }

    /*public void calendarView(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2+"-"+i1+1+"-"+i;

                ArrayList<Movies> filteredMovies1 = new ArrayList<>();
                for (Movies movies: arrayList){
                    if (movies.getDate() == date){
                        filteredMovies1.add(movies);
                    }
                }
                Adapter adapter = new Adapter(filteredMovies1, MainActivity.this);
                listView.setAdapter(adapter);
            }
        });
    }*/

    public void listView(){

        Adapter adapter = new Adapter(arrayList, MainActivity.this);
        listView.setAdapter(adapter);
    }



    /*Bring arraylist from "Read xml" class*/
    public void readXML(){

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i< list.getLength(); i++){
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Title").item(0).getTextContent();
                    int id = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    String starttame = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        Date date = format.parse(starttame);
                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/

                    arrayList.add(new Movies(name, id, starttame));
                }

                listView();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}