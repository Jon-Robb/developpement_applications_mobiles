package com.example.annexe_1_shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Vector;

public class ListeActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        listView = findViewById(R.id.listView);

//        On remplit un arrayadapter avec un vector de strings dans lequel on a mit les donnees du fichier sharedPreferences
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getMemos());
        listView.setAdapter(arrayAdapter);
    }

    public Vector<String> getMemos(){

//        On va chercher nos donnees dans le fichier liste et on les met dans un vector
        SharedPreferences memoSauves = getSharedPreferences("liste", Context.MODE_PRIVATE);
        HashSet<String> ensembleMemos = (HashSet<String>) memoSauves.getStringSet("ensemble", new HashSet<>());
        Vector<String> vec = new Vector<>(ensembleMemos);
        return vec;
    }

}