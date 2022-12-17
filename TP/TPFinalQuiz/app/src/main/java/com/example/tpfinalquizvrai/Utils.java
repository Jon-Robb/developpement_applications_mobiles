package com.example.tpfinalquizvrai;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.Random;

public class Utils {

    private RequestsSingleton instance;
    private Context context;

    public Utils(Context context){

        this.context = context;
        instance = RequestsSingleton.getInstance(context);

    }

    public void viewsFiller(TextView q, String question, NetworkImageView img1, Artiste a1, TextView rep1, NetworkImageView img2, Artiste a2, TextView rep2){

        q.setText(question);
        img1.setImageUrl(a1.images.get(new Random().nextInt(a1.images.size())).getUrl(), instance.getImageLoader());
        rep1.setText(a1.name);
        img2.setImageUrl(a2.images.get(new Random().nextInt(a2.images.size())).getUrl(), instance.getImageLoader());
        rep2.setText(a2.name);

    }

    public String returnSecondChildString(View view){

        LinearLayout parent = (LinearLayout) view;
        TextView enfant = (TextView) parent.getChildAt(1);
        String nom = enfant.getText().toString();
        return nom;

    }


}
