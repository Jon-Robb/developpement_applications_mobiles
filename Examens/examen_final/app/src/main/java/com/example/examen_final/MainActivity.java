package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Path;


public class MainActivity extends AppCompatActivity {

    private ImageView meteor;
    private Button btnSave;
    private EditText champRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meteor = findViewById(R.id.imgMeteor);
        btnSave = findViewById(R.id.btnSave);
        champRep = findViewById(R.id.champRep);

        Path p1 = new Path();
        p1.moveTo(1100, -100 );
        p1.lineTo(100, 500);

        Path p2 = new Path();
        p2.moveTo(100, 500);
        p2.lineTo(1000, 1600);

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(meteor, View.X, View.Y, p1);
        oa1.setDuration(20000);

        ObjectAnimator or = ObjectAnimator.ofFloat(meteor, View.ROTATION, 0f, -45f);
        or.setDuration(1000);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(meteor, View.X, View.Y, p2);
        oa2.setDuration(19000);

        AnimatorSet as = new AnimatorSet();
        as.setInterpolator(new AccelerateInterpolator());
        as.playSequentially(oa1, or, oa2);
        as.start();

    }
}