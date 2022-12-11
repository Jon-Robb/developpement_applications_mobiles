package com.example.projetpetitionnaire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class FinFragment extends Fragment {


    private Button boutonSauv;

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
        ViewGroup rootView = (ViewGroup )inflater.inflate(R.layout.fragment_fin, container, false);

//        Memes etapes que dans les fragments precedants pour aller chercher les views et ajouter l ecouteur
        boutonSauv = rootView.findViewById(R.id.boutonSauvegarder);
        Ecouteur ec = new Ecouteur();
        boutonSauv.setOnClickListener(ec);
        return rootView;
    }

    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            // sauvegarder ds fichier de sérialisation
            Membre membre = ((ConteneurFragmentsActivity)getActivity()).getM().build();
            try {
                FileOutputStream fos = getActivity().openFileOutput("fichier.ser", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(membre);
                oos.close();
                getActivity().finish();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }
}