package com.example.tpfinalquizvrai;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
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

    private TextView score;
    private TextView result;
    private ArrayList<Artiste> a;
    private Artistes artistes;
    private Artiste bonArtiste;
    private Utils utils;
    private QuestionHelper questionHelper;
    private boolean peutRepondre = true;
    private Score s;
    ObjectAnimator oa1, oa2;

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
        LinearLayout conteneurRep1 = parent.findViewById(R.id.conteneurRep1);
        LinearLayout conteneurRep2 = parent.findViewById(R.id.conteneurRep2);

        oa1 = ObjectAnimator.ofFloat(conteneurRep1, View.TRANSLATION_X, 0);
        oa2 = ObjectAnimator.ofFloat(conteneurRep2, View.TRANSLATION_X, 0);
        oa1.setDuration(2000);
        oa2.setDuration(2000);
        oa1.start();
        oa2.start();



        Context context = getContext();
        assert context != null;
        s = ((ConteneurFragmentsActivity) context).getS();
        score.setText(String.valueOf(s.getScore()));

        utils = new Utils(getContext());
        questionHelper = new QuestionHelper();

        RequeteTermineeListener requeteTermineeListener = response -> {
            Gson gson = new GsonBuilder().create();
            artistes = gson.fromJson(String.valueOf(response), Artistes.class);
            a = artistes.getTopArtists(2);
            bonArtiste = questionHelper.generateFollowersAnswer(a);
            utils.viewsFiller(q1, "Quel artiste a le plus de followers ?", img11, a.get(0), rep11, img12, a.get(1), rep12);
        };

        Ecouteur ec = new Ecouteur();
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

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            if (peutRepondre){
                String nom = utils.returnSecondChildString(view);
                Artiste artRep = artistes.getArtiste(nom);
                if (artRep == bonArtiste){
                    view.setBackgroundColor(Color.GREEN);
                    s.setScore(10);
                    score.setText(String.valueOf(s.getScore()));
                    result.setText("Bonne reponse!!!\n" + artRep.followers.total + " followers");

                }
                else{
                    view.setBackgroundColor(Color.RED);
                    result.setText("Mauvaises reponse\nBonne reponse : " + bonArtiste.name + ", " + bonArtiste.followers.total + " followers");
                }
                peutRepondre = false;
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