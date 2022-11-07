package com.example.annexe_2_exercices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {



    Button btnNbLignes, btnNbChar, btnNbC, btnAjouter;
    EditText champNom;
    TextView champLignes, champChar, champC;
    Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAjouter = findViewById(R.id.btnNom);
        btnNbC = findViewById(R.id.btnNbC);
        btnNbChar = findViewById(R.id.btnNbChar);
        btnNbLignes = findViewById(R.id.btnNbLignes);
        champNom = findViewById(R.id.champNom);
        champLignes = findViewById(R.id.champNbLignes);
        champChar = findViewById(R.id.champNbChar);
        champC = findViewById(R.id.champNbC);

        ec = new Ecouteur();

        btnNbLignes.setOnClickListener(ec);
        btnNbChar.setOnClickListener(ec);
        btnNbC.setOnClickListener(ec);
        btnAjouter.setOnClickListener(ec);

    }

    public class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == btnNbLignes){
                champLignes.setText(String.valueOf(compterNbLignes()));
            }
            else if (source == btnAjouter){
                if (!champNom.equals("")){
                    ecrireNom(champNom.getText().toString());
                }
                else{
                    champNom.setText("Entrez un nom à ajouter au fichier");
                }
            }
            else if (source == btnNbChar){
                champChar.setText(String.valueOf(compterNbChar()));
            }
            else{
                champC.setText(String.valueOf(compterNbC()));
            }
        }
    }

    public void fermerFluxRead(Reader r){
        try {
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fermerFluxWrite(Writer w){
        try {
            w.close(); //close appelle le flush qui va vider le flux de données vers la destination(le fichier texte)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int compterNbLignes(){

        int count = 0;
        BufferedReader br = null;

        try{
            FileInputStream fis = openFileInput("fichier_texte.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String line = br.readLine();

            while(line != null){
               count++;
               line = br.readLine();
           }

        }
        catch(IOException e ){
            e.printStackTrace();
        }
        finally {
            fermerFluxRead(br);
        }
        return count;

    }

    public int compterNbChar(){

        BufferedReader br = null;
        int c;
        int count = 0;
        try {

            FileInputStream fis = openFileInput("fichier_texte.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String ligne  = br.readLine();

            while(ligne != null){
                count += ligne.length();
                ligne = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fermerFluxRead(br);
        }
        return count;

    }

    public int compterNbC(){

        BufferedReader br = null;


        int count = 0;
        try {

            FileInputStream fis = openFileInput("fichier_texte.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            int c = br.read();

            while(c != -1){
                if ((char)c == 'c') {
                    count++;
                }
                c = br.read();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fermerFluxRead(br);
        }
        return count;
    }

    public void ecrireNom(String nom){

        BufferedWriter bw = null;
        try {

            FileOutputStream fos = openFileOutput("fichier_texte.txt", Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(nom);
            bw.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            fermerFluxWrite(bw);
        }


    }

    public int scannerMots(){

        int compteur = 0;
        Scanner sc = null;
        try {
            FileInputStream fis = openFileInput("fichier_texte.txt");
            sc = new Scanner(fis);

            while (sc.hasNext()) {
                compteur++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        finally {

            sc.close();
            return compteur;
        }
    }

}