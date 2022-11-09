package com.example.annexe_3c_serialisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    SeekBar sonnerieSB, mediaSB, notifSB;
    Vector<Integer> vec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonnerieSB = findViewById(R.id.sonnerieSB);
        mediaSB = findViewById(R.id.mediaSB);
        notifSB = findViewById(R.id.notifSB);

        try {
            vec = (Vector<Integer>)recupererValeur();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(vec != null){
            sonnerieSB.setProgress(vec.elementAt(0));
            mediaSB.setProgress(vec.elementAt(1));
            notifSB.setProgress(vec.elementAt(2));
        }


    }

    private void serialiserValeur(int valeur) throws IOException {

        FileOutputStream fos = MainActivity.this.openFileOutput("valeurs.ser", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(valeur);
        oos.close();
    }

    private Vector<Integer> recupererValeur() throws IOException, ClassNotFoundException {

        FileInputStream fis = MainActivity.this.openFileInput("valeurs.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Vector<Integer> vec;
        vec = (Vector<Integer>)ois.readObject();
        ois.close();
        return vec;
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            serialiserValeur(sonnerieSB.getProgress());
            serialiserValeur(mediaSB.getProgress());
            serialiserValeur(notifSB.getProgress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}