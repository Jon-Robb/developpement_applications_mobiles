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
import java.util.Random;


public class Question2 extends Fragment {

    private TextView score;
    private TextView result;
    private ArrayList<Artiste> a;
    private Artistes artistes;
    private Artiste bonArtiste;
    private Utils utils;
    private QuestionHelper questionHelper;
    private boolean peutRepondre = true;
    private Score s;
    StringBuilder genres;


    public Question2() {
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
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_question2, container, false);

        TextView q1 = parent.findViewById(R.id.q2);
        result = parent.findViewById(R.id.result2);
        TextView rep11 = parent.findViewById(R.id.reponse21);
        TextView rep12 = parent.findViewById(R.id.reponse22);
        score = parent.findViewById(R.id.score);
        NetworkImageView img11 = parent.findViewById(R.id.img21);
        NetworkImageView img12 = parent.findViewById(R.id.img22);

        LinearLayout conteneurRep1 = parent.findViewById(R.id.conteneurRep21);
        LinearLayout conteneurRep2 = parent.findViewById(R.id.conteneurRep22);
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(conteneurRep1, View.TRANSLATION_X, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(conteneurRep2, View.TRANSLATION_X, 0);
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
            bonArtiste = new Artiste();
            bonArtiste = a.get(new Random().nextInt(a.size()));
            genres = new StringBuilder();
            for (int i = 0; i < 3; i++){
                genres.append(bonArtiste.genres.get(i)).append(", ");
            }
            utils.viewsFiller(q1, "À quel artiste correspond ces genres musicaux : \n" + genres, img11, a.get(0), rep11, img12, a.get(1), rep12);
        };

        EcouteurQ2 ec = new EcouteurQ2();
        conteneurRep1.setOnClickListener(ec);
        conteneurRep2.setOnClickListener(ec);

        RequeteListener requeteListener = new RequeteListener(requeteTermineeListener);
        RequeteJSON requete = new RequeteJSON();

        UrlGenerator urlGenerator = new UrlGenerator();

        String url = urlGenerator.generateTwoArtistsUrl();
        requete.faireRequete(getContext(), Request.Method.GET, url, requeteListener);

        return parent;
    }

    public class EcouteurQ2 implements View.OnClickListener{

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
                    result.setText("Bonne reponse!!!\n" + artRep.name);
                }
                else{
                    view.setBackgroundColor(Color.RED);
                    result.setText("Mauvaises reponse\nBonne reponse : " + bonArtiste.name);
                }
                peutRepondre = false;
            }
        }
    }
}