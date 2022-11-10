package com.example.annexe_4_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Vector<Hashtable<String, String>> vec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        vec = new Vector<>();

        try {
            vec = getInfos();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String string[] = {"pos", "chanson", "date"};
        int integers[] = {R.id.position, R.id.chanson, R.id.date};

        @SuppressLint("ResourceType") SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, vec, R.layout.list_enfant, string, integers);

        listView.setAdapter(simpleAdapter);



    }



    public Vector<Hashtable<String, String>> getInfos() throws FileNotFoundException {

        FileInputStream fis = openFileInput("palmares.txt");
        Scanner sc = new Scanner(fis);
        Vector<Hashtable<String, String>> vecHash = new Vector<>();
        sc.useDelimiter(",|\\r\\n");

        while (sc.hasNext()){
            Hashtable pos = new Hashtable();
            Hashtable chanson = new Hashtable();
            Hashtable date = new Hashtable();
            pos.put("pos", sc.next());
            chanson.put("chanson", sc.next());
            date.put("date", sc.next());
            vecHash.add(pos);
            vecHash.add(chanson);
            vecHash.add(date);
        }
        sc.close();
       return vecHash;

    }

}