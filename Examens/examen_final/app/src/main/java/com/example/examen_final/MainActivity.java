package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.appsearch.ReportSystemUsageRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Path;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private ImageView meteor;
    private Button btnSave;
    private EditText champRep;
    private String url = "https://api.jsonbin.io/v3/b/639e8ed7dfc68e59d56b0a6e?meta=false";
    private RequestQueue queue;
    private JsonObjectRequest jsor;
    private Vector<String> vecChansons;
    private int nbBonneRep = 0;
    private Vector<String> vecBonneRep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meteor = findViewById(R.id.imgMeteor);
        btnSave = findViewById(R.id.btnSave);
        champRep = findViewById(R.id.champRep);

        Path p1 = new Path();
        p1.moveTo(1000, 0 );
        p1.lineTo(100, 500);

        Path p2 = new Path();
        p2.moveTo(100, 500);
        p2.lineTo(800, 1200);

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(meteor, View.X, View.Y, p1);
        oa1.setDuration(20000);

        ObjectAnimator or = ObjectAnimator.ofFloat(meteor, View.ROTATION, 0f, -45f);
        or.setDuration(1000);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(meteor, View.X, View.Y, p2);
        oa2.setDuration(19000);

        AnimatorSet as = new AnimatorSet();
        as.setInterpolator(new AccelerateInterpolator());

        AnimatorSet as2 = new AnimatorSet();
        ObjectAnimator oaSX = ObjectAnimator.ofFloat(meteor, View.SCALE_Y, 10);
        ObjectAnimator oaSY = ObjectAnimator.ofFloat(meteor, View.SCALE_X, 10);
        ObjectAnimator oaA = ObjectAnimator.ofFloat(meteor, View.ALPHA, 0);
        as2.setDuration(5000);
        as2.playTogether(oaSX,oaSY,oaA);

        as.playSequentially(oa1, or, oa2, as2);
        as.start();

        as2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (nbBonneRep > 0){
                    btnSave.setText("FÉLICITATIONS : " + nbBonneRep + " BONNES RÉPONSES");
                }
                else{
                    btnSave.setText("POURRI : " + nbBonneRep + " BONNES RÉPONSES");

                }
                btnSave.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        vecChansons = new Vector<>();
        vecBonneRep = new Vector<>();

        queue = Volley.newRequestQueue(this);
        jsor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray tracks = response.getJSONArray("tracks");
                    for (int i = 0; i < tracks.length(); i++){
                        JSONObject track = tracks.getJSONObject(i);
                        String titre = track.getString("name");
                        vecChansons.add(titre);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        }, error -> System.out.println(error));

        queue.add(jsor);

        btnSave.setOnClickListener(btn ->{
            String rep = champRep.getText().toString();
            String text = "";
            if (vecChansons.contains(rep)){
                if (!vecBonneRep.contains(rep)){
                    text = "+1";
                    vecBonneRep.add(rep);
                    ++nbBonneRep;
                }
                else{
                    text = "Réponse déjà donnée";
                }
            }
            else{
                text = "Pourri";
            }
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        });

    }
}