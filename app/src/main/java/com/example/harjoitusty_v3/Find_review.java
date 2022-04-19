package com.example.harjoitusty_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.AssetManager;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Find_review extends OptionMenuActivity {

    private Toolbar toolbar;
    String data = null;
    ArrayList<String> userData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_review);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readFromAssets();
    }

    public void readFromAssets() {
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream is = getAssets().open("movies.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);
        } catch (XmlPullParserException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Movies2> movies = new ArrayList<>();
        int eventType = parser.getEventType();
        Movies2 currentmovie = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("Event".equals(eltName)) {
                        currentmovie = new Movies2(); //name ja id laittaa?
                        movies.add(currentmovie);
                    } else if (currentmovie != null) {
                        if ("Title".equals(eltName)) {
                            currentmovie.name = parser.nextText();
                        } else if ("ID".equals(eltName)) {
                            currentmovie.id = parser.nextText();
                        }

                    }
                    break;
            }

            eventType = parser.next();

        }



    }
        //AssetManager am = getAssets();
        /*try{
            InputStream is = getAssets().open("movies.xml");
            //is = getApplicationContext().openFileInput("movies.xml");

            InputStreamReader isr = new InputStreamReader(is);
            //char[] inputBuffer = null;
            //inputBuffer = new char[is.available()];
            data = new String(String.valueOf(isr));
            isr.close();
            is.close();



            //InputStream is = am.open("movies.xml");
            /*System.out.println("tiedosto avattu");
            String result = IOHelper.stringFromStream(is);
            System.out.println(result);*/
        /*} catch (IOException e) {
            e.printStackTrace();
        }


        //String userName = userData.get(0);
        //String password = userData.get(1);

    }*/
}
/*https://www.youtube.com/watch?v=A0HufeFDsFA*/
/*class IOHelper{
    public static String stringFromStream(InputStream is){
        try{

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null)
                sb.append(line).append("\n");
            reader.close();
            return sb.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}*/