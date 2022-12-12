package com.example.annexe_7b_animationboutons;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    private Button btn1, btn2;
    private int btn1Score = 0, btn2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        On va chercher les metrics de l ecran
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        Ecouteur ec = new Ecouteur();

        btn1.setOnClickListener(ec);
        btn2.setOnClickListener(ec);

//        On cree deux objets paths
        Path pathBtn1 = new Path();
        Path pathBtn2 = new Path();

//        On envoie les objets path a un endroit aleatoire par rapport aux mesures de l ecran
        pathBtn1.moveTo(new Random().nextFloat() * width, new Random().nextFloat() * height);
        pathBtn2.moveTo(new Random().nextFloat() * width, new Random().nextFloat() * height);

//        On fait un pattern aleatoire pour les mouvements des boutons
        for (int i = 0; i < 10; i++){
           pathBtn1.lineTo(new Random().nextFloat() * width, new Random().nextFloat() * height);
           pathBtn2.lineTo(new Random().nextFloat() * width, new Random().nextFloat() * height);
        }

//        On cree deux objets OA qui se deplaceront sur les deux axes de facon lineaire durant 10sec
//         a l infini et repete
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn1, View.X, View.Y, pathBtn1);
        oa1.setRepeatCount(ValueAnimator.INFINITE);
        oa1.setRepeatMode(ValueAnimator.REVERSE);
        oa1.setDuration(10000);
        oa1.setInterpolator(new LinearInterpolator());

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn2, View.X, View.Y, pathBtn2);
        oa2.setRepeatCount(ValueAnimator.INFINITE);
        oa2.setRepeatMode(ValueAnimator.REVERSE);
        oa2.setDuration(10000);
        oa2.setInterpolator(new LinearInterpolator());

        oa1.start();
        oa2.start();


    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View btn) {

//            On ajoute des points sur les boutons lorqu il sont cliques
            if (btn == btn1){
                ++btn1Score;
                btn1.setText(String.valueOf(btn1Score));
            }
            else{
                ++btn2Score;
                btn2.setText(String.valueOf(btn2Score));
            }
        }
    }


}