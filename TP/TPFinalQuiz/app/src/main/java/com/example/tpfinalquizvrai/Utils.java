package com.example.tpfinalquizvrai;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    public void serialiser(Activity activity, Object s){
        try {
            FileOutputStream fos = activity.openFileOutput("fichier.ser", Context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            oos.close();
            activity.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public ArrayList<Score> getSerializedScores(Activity activity){
//        FileInputStream fis;
//        ArrayList<Score> arrScore = new ArrayList<>();
//        try {
//            fis = activity.openFileInput("fichier.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            while(ois.readObject() != null){
//                arrScore.add((Score) ois.readObject());
//            }
//            ois.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return arrScore;
//    }

    public void getSerializedScore(Activity activity, TextView bestScore){
        FileInputStream fis = null;
        try {
            fis = activity.openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Score s = (Score) ois.readObject();
            ois.close();
            bestScore.setText(String.valueOf(s.getScore()));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}

