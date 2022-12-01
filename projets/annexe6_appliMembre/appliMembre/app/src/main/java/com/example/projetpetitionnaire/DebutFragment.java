package com.example.projetpetitionnaire;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class DebutFragment extends Fragment {



    EditText champNom;
    EditText champPrenom;
    RadioGroup groupe;

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
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_debut, container, false);
        champNom =parent.findViewById(R.id.champNom);
        champPrenom = parent.findViewById(R.id.champPrenom);
        groupe = parent.findViewById(R.id.groupe);

        return parent;
    }

    @Override
    public void onPause() {
        super.onPause();
        // à compléter
        if (!champNom.equals("") && !champPrenom.equals("")){
            ConteneurFragmentsActivity activity = (ConteneurFragmentsActivity)getActivity();
            assert activity != null;
            activity.m.setNom(champNom.getText().toString());
            activity.m.setPrenom(champPrenom.getText().toString());

            int id = groupe.getCheckedRadioButtonId();
            RadioButton radioButton = groupe.findViewById(id);

            activity.m.setObjectif(radioButton.getText().toString());
            System.out.println(radioButton.getText().toString());
        }



    }
}