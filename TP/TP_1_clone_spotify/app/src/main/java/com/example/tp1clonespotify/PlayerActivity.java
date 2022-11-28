package com.example.tp1clonespotify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.Track;


public class PlayerActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "b2b20661ad71475b81e4b19305de50e6";
    private static final String REDIRECT_URI = "com.example.tp1clonespotify://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private ImageView imgStartPause, imgRewind, imgSkipBack, imgSkipNext, imgReturn, imgFastForward;
    private SpotifyDiffuseur instance;
    private TextView artistName, songTitle, albumName;
    private boolean isPlayBtn = true;
    private ImageView songImage;
    private Uri uri;

    private String playlist = "spotify:playlist:2I9t0VoXbhjgCwlQ4LasO9";


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        imgStartPause = findViewById(R.id.imgStartPause);
        imgRewind = findViewById(R.id.imgRewind);
        imgSkipBack = findViewById(R.id.imgSkipBack);
        imgSkipNext  = findViewById(R.id.imgSkipNext);
        artistName = findViewById(R.id.playlistName);
        songTitle = findViewById(R.id.songTitle);
        albumName = findViewById(R.id.songArtist);
        songImage = findViewById(R.id.songImg);

        instance = SpotifyDiffuseur.getInstance(PlayerActivity.this);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onStart() {
        super.onStart();

        instance.seConnecter();


//            ConnectionParams connectionParams =
//                    new ConnectionParams.Builder(CLIENT_ID)
//                            .setRedirectUri(REDIRECT_URI)
//                            .showAuthView(true)
//                            .build();
//
//            SpotifyAppRemote.connect(this, connectionParams,
//                    new Connector.ConnectionListener() {
//
//                        public void onConnected(SpotifyAppRemote spotifyAppRemote) {
//                            mSpotifyAppRemote = spotifyAppRemote;
//                            Log.d("MainActivity", "Connected! Yay!");
//
//                            // Now you can start interacting with App Remote
//                            startPlaylist();
//
//                        }
//
//                        public void onFailure(Throwable throwable) {
//                            Log.e("MyActivity", throwable.getMessage(), throwable);
//
//                            // Something went wrong when attempting to connect! Handle errors here
//                        }
//                    });

        imgStartPause.setOnClickListener(source -> {
            if (!isPlayBtn){
                instance.play(playlist);
                instance.getmSpotifyAppRemote().getPlayerApi()
                        .subscribeToPlayerState()
                        .setEventCallback(playerState -> {
                            final Track track = playerState.track;

                            if (track != null) {
                                artistName.setText(track.artist.name);
                                songTitle.setText(track.name);
                                albumName.setText(track.album.name);
                                instance.getmSpotifyAppRemote().getImagesApi().getImage(track.imageUri).setResultCallback(data -> {
                                    songImage.setImageBitmap(data);
                                });
                            }
                        });

                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                isPlayBtn = true;
            }
            else{
                instance.pause();
                isPlayBtn = false;
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.play));
            }
        });

        imgSkipNext.setOnClickListener(source -> {
            instance.next();
        });

        imgSkipBack.setOnClickListener(source -> {
            instance.back();
        });

    }

    private void rafraichir(Chanson chanson){
        artistName.setText(chanson.getArtiste().getNom());
        songTitle.setText(chanson.getNom());
        albumName.setText(chanson.getAlbum());
        songImage.setImageBitmap(chanson.getImgChanson());
    }

    private class CallBackImage implements CallResult.ResultCallback{
        @Override
        public void onResult(Object data) {
            Bitmap b = (Bitmap)data;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        instance.pause();
        instance.seDeconnecter();
    }



//    private void startPlaylist() {
//        // Play a playlist
//        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:2I9t0VoXbhjgCwlQ4LasO9");
//
//
//        // Subscribe to PlayerState
//        mSpotifyAppRemote.getPlayerApi()
//                .subscribeToPlayerState()
//                .setEventCallback(playerState -> {
//                    final Track track = playerState.track;
//
//                    if (track != null) {
//                        Log.d("MainActivity", track.name + " by " + track.artist.name);
//                        Log.d("yo" ,track.artist + "" + track.duration + track.album + track.imageUri +  track.uri);
//                    }
//                });
//    }



}


