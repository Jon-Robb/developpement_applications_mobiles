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

//        On cree un ObjectAnimator dans lequel on passe le conteneur, un constante de translation
//          et la valeur 0 pour le ramener a son point d origine (puisqu on l a dessendue manuellement dans
//          le XML
        oa = ObjectAnimator.ofFloat(conteneur, View.TRANSLATION_Y, 0);

        conteneur.setOnClickListener(source -> {
//            Si le menu est non visible, on start l animation, sinon, on la reverse ( effet de cache/apparant)
//              et on inverse la booleenne du toggle
            if (!isShowing){
                oa.start();
            }
            else{
                oa.reverse();
            }
            isShowing = !isShowing;

        });
    }

}