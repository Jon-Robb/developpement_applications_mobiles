package com.example.examen_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;

public class TotalActivity extends AppCompatActivity {

    private TextView champTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        champTotal = findViewById(R.id.champTotal);
        DecimalFormat df = new DecimalFormat("###,###.00$");
        FileInputStream fis = null;
        try {
            fis = openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Commande c = (Commande) ois.readObject();
            ois.close();
            champTotal.setText("Votre total : " + df.format(c.calculerCommande()));
        }
        catch (IOException | ClassNotFoundException e){
            Toast.makeText(TotalActivity.this, "Ça marche pas ton affaire!", Toast.LENGTH_LONG ).show();
        }

    }
}