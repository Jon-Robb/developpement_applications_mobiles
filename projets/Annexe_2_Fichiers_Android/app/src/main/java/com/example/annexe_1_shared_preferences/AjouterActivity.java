package com.example.annexe_1_shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class AjouterActivity extends AppCompatActivity {

    Button btnAjouterAjouter;
    EcouteurAjouter ec;
    EditText champMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        btnAjouterAjouter = findViewById(R.id.btnAjouterAjouter);
        champMemo = findViewById(R.id.champMemo);

        ec = new EcouteurAjouter();
        btnAjouterAjouter.setOnClickListener(ec);

    }

    public class EcouteurAjouter implements View.OnClickListener{

        @Override
        public void onClick(View source) {





        }
    }
}