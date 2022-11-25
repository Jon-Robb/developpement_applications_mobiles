package com.example.tp1clonespotify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "b2b20661ad71475b81e4b19305de50e6";
    private static final String REDIRECT_URI = "com.example.tp1clonespotify://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    Button btnPlay;
    SpotifyDiffuseur instance;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btnPlay);

        instance = SpotifyDiffuseur.getInstance(MainActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnPlay.setOnClickListener(source -> {

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
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
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