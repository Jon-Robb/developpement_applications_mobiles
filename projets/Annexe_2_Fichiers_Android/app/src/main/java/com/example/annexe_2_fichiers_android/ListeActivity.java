package com.example.annexe_2_fichiers_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;

public class ListeActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        listView = findViewById(R.id.listView);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getMemos());
        listView.setAdapter(arrayAdapter);

    }

    public void fermerFlux(Reader r){
        try {
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<String> getMemos(){

        BufferedReader br = null;
        Vector<String> vec = null;
        try {
            FileInputStream fis = openFileInput("Memos.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            vec = new Vector<>();
            String line;

            while((line = br.readLine()) != null){
                vec.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fermerFlux(br);
        }
        return vec;

    }

}