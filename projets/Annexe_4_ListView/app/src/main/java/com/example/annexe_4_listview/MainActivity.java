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

//        On va chercher notre fichier en l entourant d un try/catch au cas ou il n existe pas
        try {
            vec = getInfos();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        Le tableau de strings doit correspondre aux clees des valeurs de notre hashtable
        String string[] = {"pos", "chanson", "date"};
//        Le tableau de Integers ici doit correspondre aux endroits ou on veut faire apparaitre les donnees dans notre list view
        int integers[] = {R.id.position, R.id.chanson, R.id.date};

//        On cree le simpleAdapter en lui donnant le contexte, les donnees (le vecteur de hashtable qu on est alle chercher), le listview dans le layout
//          dans lequel on veut ajouter les infos, le tableau de strings qu on a cree plus haut et le tableau de Integers
        @SuppressLint("ResourceType") SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, vec, R.layout.list_enfant, string, integers);

        listView.setAdapter(simpleAdapter);

    }



    public Vector<Hashtable<String, String>> getInfos() throws FileNotFoundException {

//        On va chercher nos donnees dans le fichier palmares.txt
        FileInputStream fis = openFileInput("palmares.txt");
//        On utilise un scanner pour lire les donnees
        Scanner sc = new Scanner(fis);
//        On cree un hashtable qui utilisera une cle string avec une donnee string
        Vector<Hashtable<String, String>> vecHash = new Vector<>();
//        On utilise soit la virgule, soit un saut de ligne pour delimiter nos donnees
        sc.useDelimiter(",|\\r\\n");

        while (sc.hasNext()){

//            A chaque next, (grace au delimiteur), on ajoute l element voulu associe a la clee correspondante.
            Hashtable hashtable = new Hashtable();
            hashtable.put("pos", sc.next());
            hashtable.put("chanson", sc.next());
            hashtable.put("date", sc.next());
            vecHash.add(hashtable);

        }
        sc.close();
       return vecHash;

    }

}