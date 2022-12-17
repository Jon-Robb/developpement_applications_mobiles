package com.example.tpfinalquizvrai;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class Utils {

    private RequestsSingleton instance;

    public Utils(Context context){

        instance = RequestsSingleton.getInstance(context);

    }

    public void viewsFiller(TextView q, String question, NetworkImageView img1, Artiste a1, TextView rep1, NetworkImageView img2, Artiste a2, TextView rep2){

        q.setText(question);
        int index1 = a1.randomFromVecImg();
        int index2 = a2.randomFromVecImg();
        String image1 = a1.images.get(index1).getUrl();
        String image2 = a2.images.get(index2).getUrl();
        img1.setImageUrl(image1, instance.getImageLoader());
        rep1.setText(a1.name);
        img2.setImageUrl(image2, instance.getImageLoader());
        rep2.setText(a2.name);

    }

    public String returnSecondChildString(View view){

        LinearLayout parent = (LinearLayout) view;
        TextView enfant = (TextView) parent.getChildAt(1);
        String nom = enfant.getText().toString();
        return nom;

    }


}
