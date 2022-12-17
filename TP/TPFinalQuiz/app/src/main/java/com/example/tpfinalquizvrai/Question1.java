package com.example.tpfinalquizvrai;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class Question1 extends Fragment {

    private RequestsSingleton instance;
    private Artiste a1;
    private Artiste a2;
    private TextView score;
    private TextView result;
    private LinearLayout conteneurRep1, conteneurRep2;
    private Ecouteur ec;
    private ArrayList<Artiste> a;

    public Question1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        On transtype le inflater en ViewGroup, pour avoir acces aux views de ce fragment particulier
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.activity_question1, container, false);
        TextView q1 = parent.findViewById(R.id.q1);
        result = parent.findViewById(R.id.result1);
        TextView rep11 = parent.findViewById(R.id.reponse11);
        TextView rep12 = parent.findViewById(R.id.reponse12);
        score = parent.findViewById(R.id.score);
        NetworkImageView img11 = parent.findViewById(R.id.img11);
        NetworkImageView img12 = parent.findViewById(R.id.img12);
        conteneurRep1 = parent.findViewById(R.id.conteneurRep1);
        conteneurRep2 = parent.findViewById(R.id.conteneurRep2);

        instance = RequestsSingleton.getInstance(getContext());


        RequeteTermineeListener requeteTermineeListener = response -> {
            Gson gson = new GsonBuilder().create();
            Artistes artistes = gson.fromJson(String.valueOf(response), Artistes.class);
            a = artistes.getTopArtists(2);
            new ViewsFiller(getContext()).fillViews(q1, "Quel artiste a le plus de followers ?", img11, a.get(0), rep11, img12, a.get(1), rep12);
        };

        ec = new Ecouteur();
        conteneurRep1.setOnClickListener(ec);
        conteneurRep2.setOnClickListener(ec);

        RequeteListener requeteListener = new RequeteListener(requeteTermineeListener);
        RequeteJSON requete = new RequeteJSON();

        UrlGenerator urlGenerator = new UrlGenerator();

        String url = urlGenerator.generateTwoArtistsUrl();
        requete.faireRequete(getContext(), Request.Method.GET, url, requeteListener);

        return parent;
    }


    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            LinearLayout parent = (LinearLayout) view;
            TextView enfant = (TextView) parent.getChildAt(1);
            String nom = enfant.getText().toString();
            if (nom.equals(a.get(0).name)){
                parent.setBackgroundColor(Color.GREEN);
                score.setText(String.valueOf(10));
                result.setText("Bonne reponse!");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // onPause veut dire que le fragment a ete change, donc qu on prend les informations et on les
//           ajoute dans le builder, qui construit un membre au fil des fragments


    }
}