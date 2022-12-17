package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(source -> {
            startActivity(new Intent(MainActivity.this, ConteneurFragmentsActivity.class));
        });
    }


}