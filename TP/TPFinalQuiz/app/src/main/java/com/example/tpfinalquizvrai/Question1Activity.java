package com.example.tpfinalquizvrai;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Question1Activity extends Fragment {

    private RequestsSingleton instance;
    private Artiste a1;
    private Artiste a2;
    private TextView score;
    private TextView result;
    LinearLayout conteneurRep1, conteneurRep2;
    Ecouteur ec;

    public Question1Activity() {
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


        Random random = new Random();

        RequeteTermineeListener requeteTermineeListener = response -> {
            Gson gson = new GsonBuilder().create();
            Artistes artistes = gson.fromJson(String.valueOf(response), Artistes.class);
            Collections.shuffle(artistes.artists);
            q1.setText("Quel artise a le plus de followers ?");
            a1 = artistes.artists.get(0);
            img11.setImageUrl(a1.images.get(random.nextInt(a1.images.size())).getUrl(), instance.getImageLoader());
            rep11.setText(a1.name);
            a2 = artistes.artists.get(1);
            img12.setImageUrl(a2.images.get(random.nextInt(a2.images.size())).getUrl(), instance.getImageLoader());
            rep12.setText(a2.name);

        };

        ec = new Ecouteur();
        conteneurRep1.setOnClickListener(ec);
        conteneurRep2.setOnClickListener(ec);

        RequeteListener requeteListener = new RequeteListener(requeteTermineeListener);
        RequeteJSON requete = new RequeteJSON();

        String url = "https://api.spotify.com/v1/artists?ids=4Z8W4fKeB5YxbusRsdQVPb%2C12Chz98pHFMPJEknJQMWvI";
        requete.faireRequete(getContext(), Request.Method.GET, url, requeteListener);

        return parent;
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            LinearLayout parent = (LinearLayout) view;
            TextView enfant = (TextView) parent.getChildAt(1);
            String nom = enfant.getText().toString();
            if (nom.equals(a1.name)){
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