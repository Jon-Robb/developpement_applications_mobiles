package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nomEric = findViewById(R.id.nomEric);
        ImageView imgEric = findViewById(R.id.imgEric);


        ObjectAnimator oaScaleY = ObjectAnimator.ofFloat(nomEric, View.SCALE_Y, 1);
        ObjectAnimator oaScaleX = ObjectAnimator.ofFloat(nomEric, View.SCALE_X, 1);
        ObjectAnimator oaAlpha = ObjectAnimator.ofFloat(nomEric, View.ALPHA, 1);
        AnimatorSet as  = new AnimatorSet();
        as.playTogether(oaScaleX,oaScaleY,oaAlpha);
        as.setInterpolator(new BounceInterpolator());
        as.setDuration(3000);
        as.start();


        ObjectAnimator oRotate = ObjectAnimator.ofFloat(imgEric, View.ROTATION, 3600);
        ObjectAnimator oaScaleImgY = ObjectAnimator.ofFloat(imgEric, View.SCALE_Y, 1);
        ObjectAnimator oaScaleImgX = ObjectAnimator.ofFloat(imgEric, View.SCALE_X, 1);
        ObjectAnimator oaImgAlpha = ObjectAnimator.ofFloat(imgEric, View.ALPHA, 1);
        AnimatorSet as2 = new AnimatorSet();
        as2.playTogether(oRotate, oaScaleImgX, oaScaleImgY, oaImgAlpha);
        as.setInterpolator(new DecelerateInterpolator());
        as2.setDuration(5000);
        as2.start();

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(source -> {
            startActivity(new Intent(MainActivity.this, ConteneurFragmentsActivity.class));
        });
    }


}