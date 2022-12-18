package com.example.tpfinalquizvrai;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;


public class FinFragment extends Fragment {


    private Button btnSave, btnQuitter;
    private TextView scoreFinal;
    private ImageView etoile1, etoile2, etoile3, etoile4;
    private Score s;
    ObjectAnimator o1, o2, o3, o4;
    EcouteurFin ec;


    public FinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_fin, container, false);
        btnSave = parent.findViewById(R.id.btnSave);
        btnQuitter = parent.findViewById(R.id.btnRetour);
        scoreFinal = parent.findViewById(R.id.scoreFin);
        etoile1 = parent.findViewById(R.id.imgEtoile1);
        etoile2 = parent.findViewById(R.id.imgEtoile2);
        etoile3 = parent.findViewById(R.id.imgEtoile3);
        etoile4 = parent.findViewById(R.id.imgEtoile4);

        Context context = getContext();
        assert context != null;
        s = ((ConteneurFragmentsActivity)context).getS();
        scoreFinal.setText(String.valueOf(s.getScore()));

        o1 = ObjectAnimator.ofFloat(etoile1, View.ROTATION, 3600);
        o2 = ObjectAnimator.ofFloat(etoile2, View.ROTATION, 3600);
        o3 = ObjectAnimator.ofFloat(etoile3, View.ROTATION, 3600);
        o4 = ObjectAnimator.ofFloat(etoile4, View.ROTATION, 3600);
        o1.setDuration(10000);
        o2.setDuration(10000);
        o3.setDuration(10000);
        o4.setDuration(10000);
        o1.setInterpolator(new AccelerateDecelerateInterpolator());
        o2.setInterpolator(new AccelerateDecelerateInterpolator());
        o3.setInterpolator(new AccelerateDecelerateInterpolator());
        o4.setInterpolator(new AccelerateDecelerateInterpolator());
        o1.setRepeatCount(ValueAnimator.INFINITE);
        o2.setRepeatCount(ValueAnimator.INFINITE);
        o3.setRepeatCount(ValueAnimator.INFINITE);
        o4.setRepeatCount(ValueAnimator.INFINITE);
        o1.setRepeatMode(ValueAnimator.REVERSE);
        o2.setRepeatMode(ValueAnimator.REVERSE);
        o3.setRepeatMode(ValueAnimator.REVERSE);
        o4.setRepeatMode(ValueAnimator.REVERSE);

        o1.start();
        o2.start();
        o3.start();
        o4.start();

        ec = new EcouteurFin();
        btnSave.setOnClickListener(ec);
        btnQuitter.setOnClickListener(ec);

        return parent;
    }

    public class EcouteurFin implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == btnSave){
                if (s.getScore() > 0){

                    try{
                        FileInputStream fis = getActivity().openFileInput("scores.ser");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Scores scores = (Scores) ois.readObject();
                        scores.updateArrayScore(s);
                        ois.close();
                        FileOutputStream fos = getActivity().openFileOutput("scores.ser", Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(scores);
                    }
                    catch(IOException | ClassNotFoundException exception){
                        try {
                            FileOutputStream fos = getActivity().openFileOutput("scores.ser", Context.MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            Scores scores = new Scores();
                            scores.updateArrayScore(s);
                            oos.writeObject(scores);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
//                    try {
//                        FileOutputStream fos = getActivity().openFileOutput("fichier.ser", Context.MODE_PRIVATE);
//                        ObjectOutputStream oos = new ObjectOutputStream(fos);
//                        oos.writeObject(s);
//                        oos.close();
//                        getActivity().finish();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
                else{
                    Toast.makeText(getContext(), "Vous n'avez pas fait de point", Toast.LENGTH_LONG).show();
                }
            }
            requireActivity().finish();

        }
    }

}