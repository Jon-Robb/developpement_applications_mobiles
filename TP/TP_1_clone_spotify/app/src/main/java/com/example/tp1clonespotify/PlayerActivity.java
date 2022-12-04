package com.example.tp1clonespotify;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class PlayerActivity extends AppCompatActivity {

    private ImageView imgStartPause, imgRewind, imgSkipBack, imgSkipNext, imgReturn, imgFastForward;
    private SpotifyDiffuseur instance;
    private TextView playlistName, songTitle, albumName;
    private boolean isPlayBtn = true;
    private ImageView songImage, playlistMenu;
    private SeekBar seekBar;
    private Chronometer timeElapsed, timeLeft;
    ActivityResultLauncher<Intent> lanceur;
    int count = 0;

    private String playlist = "spotify:playlist:2I9t0VoXbhjgCwlQ4LasO9";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        imgStartPause = findViewById(R.id.imgStartPause);
        imgRewind = findViewById(R.id.imgRewind);
        imgSkipBack = findViewById(R.id.imgSkipBack);
        imgSkipNext  = findViewById(R.id.imgSkipNext);
        playlistName = findViewById(R.id.playlistName);
        songTitle = findViewById(R.id.songTitle);
        albumName = findViewById(R.id.songArtist);
        songImage = findViewById(R.id.songImg);
        seekBar = findViewById(R.id.seekBar);
        instance = SpotifyDiffuseur.getInstance(PlayerActivity.this);
        imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        timeElapsed = findViewById(R.id.timeElapsed);
        timeLeft = findViewById(R.id.timeLeft);
        imgFastForward = findViewById(R.id.imgFastForward);
        playlistMenu = findViewById(R.id.playlistMenu);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result->{
            if (result.getResultCode() == 24){
                assert result.getData() != null;
                playlist = result.getData().getStringExtra("uri");
            }
        });



    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onStart() {
        super.onStart();

        instance.seConnecter(playlist);

        imgStartPause.setOnClickListener(source -> {
            if (!isPlayBtn){
                instance.resume();
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                isPlayBtn = true;
            }
            else{
                instance.pause();
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.play));
                isPlayBtn = false;
            }
            instance.rafraichir();
        });

        imgSkipNext.setOnClickListener(source -> {
            instance.next();
        });

        imgSkipBack.setOnClickListener(source -> {
            instance.back();
        });

//        imgFastForward.setOnLongClickListener(source -> {
//
//        });


        timeElapsed.setOnChronometerTickListener(chronometer ->{
            ++count;
            seekBar.setProgress(count);
        });

        playlistMenu.setOnClickListener(source ->{
            Intent i = new Intent(PlayerActivity.this, PlaylistMenuActivity.class);
            lanceur.launch(i);
        });


    }


    public void rafraichir(Chanson chanson, Bitmap imgChanson){
        playlistName.setText(chanson.getArtiste().getNom());
        songTitle.setText(chanson.getNom());
        albumName.setText(chanson.getAlbum());
        songImage.setImageBitmap(imgChanson);
    }

    public void setSeekBarMax(int value, int progress){
        seekBar.setMax(value);
        seekBar.setProgress(progress);
    }

    public void setChronos(long base, long duration){
        timeElapsed.setBase(base);
        timeLeft.setBase(duration);
    }

    public void startChronos(){
        timeElapsed.start();
        timeLeft.start();
    }

    public void stopChronos(){
        timeLeft.stop();
        timeElapsed.stop();
    }


    @Override
    protected void onStop() {
        super.onStop();
//        instance.pause();
//        instance.seDeconnecter();
    }
}


