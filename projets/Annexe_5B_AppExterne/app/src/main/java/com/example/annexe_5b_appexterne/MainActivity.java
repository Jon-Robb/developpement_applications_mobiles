package com.example.annexe_5b_appexterne;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button btnLivre, btnHawkes, btnMarie, btnCourriel, btnPhoto;
    ImageView image;
    LinearLayout conteneur;
    Intent intent;
    ActivityResultLauncher<Intent> lanceur;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCourriel = findViewById(R.id.btnCourriel);
        btnHawkes = findViewById(R.id.btnHawkes);
        btnLivre = findViewById(R.id.btnLivre);
        btnMarie = findViewById(R.id.btnMarie);
        btnPhoto = findViewById(R.id.btnPhoto);
        conteneur = findViewById(R.id.conteneur);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallRetour());


        ec = new Ecouteur();

        for(int i = 0; i < conteneur.getChildCount() - 1; i++){
            conteneur.getChildAt(i).setOnClickListener(ec);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 2417 );
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();


//        Bundle extras = data.
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2417){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Oui, utilisez la camera", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class CallRetour implements ActivityResultCallback<ActivityResult>{

        @Override
        public void onActivityResult(ActivityResult data) {
            assert data.getData() != null;
            Bundle extras = data.getData().getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
        }
    }

    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {

            if (source == btnLivre){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.leslibraires.ca"));
                startActivity(intent);
            }
            else if (source == btnMarie){
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+14385302417"));
                startActivity(intent);
            }
            else if (source == btnHawkes){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=hawkesbury,+ontario,+canada"));
                startActivity(intent);
            }
            else if (source == btnCourriel){

            }
            else{
                intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.setType("/image/*");

            }
        }
    }

}