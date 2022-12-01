package com.example.annexe_7_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout conteneur;
    ObjectAnimator oa;
    boolean isShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conteneur = findViewById(R.id.conteneur);

        int translation = pxToDp(170);
        oa = ObjectAnimator.ofFloat(conteneur, View.TRANSLATION_Y, 0);

        conteneur.setOnClickListener(source ->{
            if (!isShowing){
                oa.start();
            }
            else{
                oa.reverse();
            }
            isShowing = !isShowing;

        });
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }



}