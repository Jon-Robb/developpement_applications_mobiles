package com.example.projetpetitionnaire;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class DeuxiemeFragment extends Fragment {



    private SeekBar seek;
    private TextView texteNiveau;

    private Ecouteur ec;

    public DeuxiemeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup )inflater.inflate(R.layout.fragment_deuxieme, container, false);

//        On va chercher nos views et on ajoute notre ecouteur sur le seekbar
        seek = rootView.findViewById(R.id.seekBar);
        texteNiveau = rootView.findViewById(R.id.champNiveau);
        ec = new Ecouteur();
        seek.setOnSeekBarChangeListener(ec);
        return rootView;

    }

    private class Ecouteur implements SeekBar.OnSeekBarChangeListener
    {

        @SuppressLint("SetTextI18n")
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            texteNiveau.setText(("Quel est votre niveau d'activité actuel :  "  + seekBar.getProgress()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
//            On va chercher notre activity pour pouvoir avoir acces au builder de membre
//              et lorsque la seekbar arrete d etre modifiee, on ajoute l information dans le membre actuel
            ConteneurFragmentsActivity activity = (ConteneurFragmentsActivity)getActivity();
            assert activity != null;
            activity.m.setDegre(seekBar.getProgress());

        }
    }


}