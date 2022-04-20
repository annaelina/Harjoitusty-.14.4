package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Find_review extends OptionMenuActivity {

    private Toolbar toolbar;
    Context context = Find_review.this;
    ListView listView;
    ArrayList<Movies2> arrayList = new ArrayList<>();
    ArrayAdapter<Movies2> adapter = null;
    //File file = new File(context.getFilesDir() + "movies.txt");
    //ArrayList<Movies2> arrayList2 = new ArrayList<>();
    //ArrayList<String> userData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_review);

        listView = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        writetoFile();
        readFile();
    }

    public void writetoFile() {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Events/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getDocumentElement().getElementsByTagName("Event");



            FileOutputStream fos = new FileOutputStream(context.getFilesDir() + "movies.txt");
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
                    /*FileWriter fw = new FileWriter(filename);
                    Writer output = new BufferedWriter(fw);
                    int size = arrayList.size();
                    for(int i=0; i<size; i++){
                        output.write(arrayList.get(i)+"\n");
                    }
                    output.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void readFile(){
        try{
            FileInputStream fis = new FileInputStream(context.getFilesDir() + "movies.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Movies2> arrayList2 = (ArrayList<Movies2>) ois.readObject();
            ois.close();

            listView(arrayList2);
            /*BufferedReader input = new BufferedReader(new FileReader(filename));
            //tähän jotain?
            String line;
            Movies2 movies2;
            while((line = input.readLine()) != null){
                arrayList2.add();
            }
            input.close();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void listView(ArrayList<Movies2> arrayList2){
        adapter = new ArrayAdapter<Movies2>(this, android.R.layout.simple_spinner_item, arrayList2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies2 selectedmovie = (Movies2) listView.getItemAtPosition(i);
                System.out.println(selectedmovie);
            }
        });
    }


}