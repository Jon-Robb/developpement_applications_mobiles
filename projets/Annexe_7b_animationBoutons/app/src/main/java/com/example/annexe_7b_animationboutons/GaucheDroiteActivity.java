package com.example.annexe_7b_animationboutons;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;


public class GaucheDroiteActivity extends AppCompatActivity {

    private Button btnDemarrer;
    private ImageView starSprite;
    private ObjectAnimator oa;
    private Path path;
    private boolean isAnimating = false, isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauche_droite);

        btnDemarrer = findViewById(R.id.btnDemarrer);
        starSprite = findViewById(R.id.starSprite);

        path = new Path();

        path.moveTo(-1000,500);

        for (int i = 0; i < 10; i++){
            path.lineTo(700, 500);
            path.lineTo(300,500);
        }

        path.lineTo(1500, 500);


        oa = ObjectAnimator.ofFloat(starSprite, View.X, View.Y, path);
        oa.setRepeatCount(ValueAnimator.INFINITE);
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.setDuration(5000);
        oa.setInterpolator(new LinearInterpolator());

        btnDemarrer.setOnClickListener(source ->{
            if (!isAnimating && !isStarted){
                oa.start();
                isAnimating = true;
                isStarted = true;
            }
            else if (isAnimating && isStarted){
                oa.cancel();
                isAnimating = false;
            }
            else if (!isAnimating) {
                oa.resume();
                isAnimating=true;
            }
        });




    }
}