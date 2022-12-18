package com.example.tpfinalquizvrai;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class FinFragment extends Fragment {


    private Button btnSave, btnQuitter;
    private TextView scoreFinal;
    private ImageView etoile1, etoile2, etoile3, etoile4;
    private Score s;


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
//        Memes etapes que dans les fragments precedants pour aller chercher les views et ajouter l ecouteur

        return parent;
    }

}