package com.example.annexe_3_serialisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    EditText champPrenom, champNom;
    Utilisateur u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        champPrenom = findViewById(R.id.champPrenom);
        champNom = findViewById(R.id.champNom);
    }

//    méthode passée au bouton dans les paramètres du layout
    public void clic( View v ){

        Intent retour = new Intent();
        u = new Utilisateur(champPrenom.getText().toString(), champNom.getText().toString());
//        Puisque notre classe est sérializable, on peut passer l'objet Utilisateur dans l'extra
        retour.putExtra("u", u);
//        On doit mettre un resultCode
        setResult(24, retour);
        finish();

    }
}