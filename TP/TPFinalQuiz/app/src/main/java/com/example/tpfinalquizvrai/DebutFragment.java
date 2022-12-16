package com.example.tpfinalquizvrai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


public class DebutFragment extends Fragment {



    public DebutFragment() {
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
//        On transtype le inflater en ViewGroup, pour avoir acces aux views de ce fragment particulier
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_debut, container, false);

        return parent;
    }

    @Override
    public void onPause() {
        super.onPause();
        // onPause veut dire que le fragment a ete change, donc qu on prend les informations et on les
//           ajoute dans le builder, qui construit un membre au fil des fragments




    }
}