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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Find_review extends OptionMenuActivity {

    private Toolbar toolbar;
    Context context;
    ArrayList<Movies2> arrayList = new ArrayList<>();
    //ArrayList<String> userData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_review);
        context = Find_review.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.out.println(context.getFilesDir());

        writetoXMLFile();
        readXML();
    }

    public void writetoXMLFile(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Events/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getDocumentElement().getElementsByTagName("Event");

            for (int i = 0; i< list.getLength(); i++){
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Title").item(0).getTextContent();
                    int id = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    //String starttame = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        Date date = format.parse(starttame);
                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/

                    arrayList.add(new Movies2(name, id));
                }

            }

            XmlSerializer serializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            try{
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8", true);
                serializer.startTag("", "movies");
                //serializer.attribute("", "number", String.valueOf(arrayList.size()));
                for (Movies2 movie : arrayList){
                    serializer.startTag("", "movie");

                    serializer.startTag("", "title");
                    serializer.text(movie.getName());
                    serializer.endTag("", "title");

                    serializer.startTag("", "ID");
                    serializer.text(String.valueOf(movie.getID()));
                    serializer.endTag("", "ID");

                    serializer.endTag("", "movie");

                }

                serializer.endTag("", "movies");
                serializer.endDocument();
                String result = writer.toString();
                writeToFile(this, "movies.xml", result);


            } catch(IOException e){
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void writeToFile(Context context, String filename, String str){
        try{
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(str.getBytes(),0, str.length());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readXML(){


    }

/*https://ssaurel.medium.com/parsing-xml-data-in-android-apps-71ef607fbb16*/
    /*public void readFromAssets() {
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream is = getAssets().open("movies.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
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
                        currentmovie = new Movies2();
                        movies.add(currentmovie);
                    } else if (currentmovie != null) {
                        if ("Title".equals(eltName)) {
                            currentmovie.name = parser.nextText();
                            System.out.println(currentmovie.name);
                        } else if ("ID".equals(eltName)) {
                            currentmovie.id = parser.nextText();
                        }

                    }
                    break;
            }
            eventType = parser.next();

        }





    } */
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