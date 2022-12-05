package com.example.annexe_7b_animationboutons;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class AnimationTitreActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView titre;
    private ObjectAnimator oaScaleY, oaScaleX, oaAlpha;
    private AnimatorSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_titre);

        btnStart = findViewById(R.id.btnStart);
        titre = findViewById(R.id.titre);

        oaScaleY = ObjectAnimator.ofFloat(titre, View.SCALE_Y, 1);
        oaScaleX = ObjectAnimator.ofFloat(titre, View.SCALE_X, 1);
        oaAlpha = ObjectAnimator.ofFloat(titre, View.ALPHA, 1);
        as = new AnimatorSet();
        as.playTogether(oaScaleX,oaScaleY,oaAlpha);
        as.setInterpolator(new BounceInterpolator());
        as.setDuration(2000);

        btnStart.setOnClickListener(source->{
            as.start();
        });
    }
}