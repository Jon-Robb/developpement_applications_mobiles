package com.example.annexe_2_fichiers_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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

        public void fermerFlux (Writer w){
            try {
                w.close(); //close appelle le flush qui va vider le flux de données vers la destination(le fichier texte)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View source) {

            BufferedWriter bw = null;

            try {
                FileOutputStream fos = openFileOutput("Memos.txt", Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
//                Il manque le buffer
                bw = new BufferedWriter(osw);
                bw.write(champMemo.getText().toString());
                bw.newLine(); //bw.write("\r\n")


            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                fermerFlux(bw);
                finish();

            }



        }
    }
}