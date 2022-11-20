package com.example.projetetudiant;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnAjouter, btnUneAutre;
    TextView champPensee;
    Ecouteur ec;
    ListePensees instance;
    ActivityResultLauncher<Intent> lanceur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAjouter = findViewById(R.id.boutonAjouter);
        btnUneAutre = findViewById(R.id.boutonUneAutre);
        champPensee = findViewById(R.id.champPensee);

        ec = new Ecouteur();
        btnUneAutre.setOnClickListener(ec);
        btnAjouter.setOnClickListener(ec);

        instance = ListePensees.getInstance(MainActivity.this);
        champPensee.setText(instance.getRandomPensee());

//        On cree le laucher en lui attribuant le callRetour
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallRetour());

//        On essaie de recuperer le fichier de serialiasation, sinon on envoie un message que celui ci n existe pas
        try {
            instance.recupererDuFichierDeSerialisation();
        } catch (IOException | ClassNotFoundException e) {
            champPensee.setText("!!! Y'a rien dans ton fichier esti de pourri !!!");
        }

    }

    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == btnAjouter){
//                Le bouton ajouter nous amene a l activiter AjouterActivity
                lanceur.launch(new Intent(MainActivity.this, AjouterActivity.class));
            }
            else{
                champPensee.setText(instance.getRandomPensee());
            }
        }
    }

    private class CallRetour implements ActivityResultCallback<ActivityResult>{

        @Override
        public void onActivityResult(ActivityResult result) {
//            Si le code correspond a celui qu on a envoye, on cree un toast avec le texte passe
            if (result.getResultCode() == 24){
                Context context = getApplicationContext();
                CharSequence text = (CharSequence) result.getData().getSerializableExtra("texte");
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
    }

    @Override
//    Quand on change d etat dans le cycle de vie, on serialise la liste
    protected void onStop() {
        super.onStop();
        instance.serialiserListe();
    }
}