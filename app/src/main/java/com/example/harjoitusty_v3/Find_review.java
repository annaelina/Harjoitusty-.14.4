package com.example.harjoitusty_v3;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Find_review extends OptionMenuActivity {

    private Toolbar toolbar;
    Context context = Find_review.this;
    ListView listView;
    public String name;
    Button button3;

    ArrayList<Movies2> arrayList = new ArrayList<>();
    ArrayList<Movies2> arrayList2 = null;
    ArrayAdapter<Movies2> adapter = null;


    //File file = new File(context.getFilesDir() + "movies.txt");
   
    //ArrayList<String> userData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_review);

        button3 = (Button) findViewById(R.id.button3);
        listView = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        writetoFile();
        this.arrayList2 = readFile();
        listView(this.arrayList2);

    }

    public void writetoFile() {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Events/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getDocumentElement().getElementsByTagName("Event");

            //tässä yritetään lukea Finnkino Events XML, laittaa tiedot Arraylistaan ja lisätä lista tiedostoon "movies.txt"
            //miten sovelluksen seuraavalla avauskerralla päivittää tiedostoa niin, että siellä olevat elokuvat säilyvät
            // --> mutta jos on tullut uusia elokuvia ne lisätään

            FileOutputStream fos = new FileOutputStream( context.getFilesDir() + "movies.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Title").item(0).getTextContent();
                    int id = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    //for loopilla, hashmap?


                    arrayList.add(new Movies2(name, id));


                }

            }
            oos.writeObject(arrayList);
            oos.close();
                
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movies2> readFile(){
        try{
            FileInputStream fis = new FileInputStream(context.getFilesDir() + "movies.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList2 = (ArrayList<Movies2>) ois.readObject();
            ois.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    public void listView(ArrayList<Movies2> arrayList2){
        adapter = new ArrayAdapter<Movies2>(this, android.R.layout.simple_list_item_activated_1, arrayList2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies2 selectedmovie = (Movies2) listView.getItemAtPosition(i);
                String name = selectedmovie.getName();
                giveReview(name);

            }

        });


    }

    public void giveReview(String name){
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Find_review.this, writereview.class);
                intent.putExtra("key", name);
                startActivity(intent);
            }
        });


    }





}