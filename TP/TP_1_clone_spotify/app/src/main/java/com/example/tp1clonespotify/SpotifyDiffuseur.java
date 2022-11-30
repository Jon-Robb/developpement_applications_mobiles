package com.example.tp1clonespotify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.SeekBar;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import java.util.Vector;
import java.util.concurrent.Flow;

public class SpotifyDiffuseur {

    private Context context;
    private static SpotifyDiffuseur instance;
    private static final String CLIENT_ID = "b2b20661ad71475b81e4b19305de50e6";
    private static final String REDIRECT_URI = "com.example.tp1clonespotify://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private PlayerState vPlayerState;

    public static SpotifyDiffuseur getInstance(Context context){
        if(instance == null){
            instance = new SpotifyDiffuseur(context);
        }
        return  instance;
    }

    public SpotifyAppRemote getmSpotifyAppRemote() {
        return mSpotifyAppRemote;
    }

    public PlayerState getvPlayerState() {
        return vPlayerState;
    }

    public void setvPlayerState(PlayerState vPlayerState) {
        this.vPlayerState = vPlayerState;
    }

    private SpotifyDiffuseur (Context context){
        this.context = context;
    }

    public void seConnecter(String playlist){

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(context, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        instance.play(playlist);
                        rafraichir();
                        // Now you can start interacting with App Remote

                        mSpotifyAppRemote.getPlayerApi()
                                .subscribeToPlayerState()
                                .setEventCallback(playerState ->{
                                    final Track track = playerState.track;
                                    if (track != null){
                                        setvPlayerState(playerState);
                                    }
                                });
                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }



                });



    }

    public void setSeekBar(SeekBar sb, int value){
        sb.setMax(value);
    }

    public void seDeconnecter(){
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    public void play(String playlist) {
        // Play a playlist
        mSpotifyAppRemote.getPlayerApi().play(playlist);
    }

    public void pause(){
        mSpotifyAppRemote.getPlayerApi().pause();
    }

    public void resume(){
        mSpotifyAppRemote.getPlayerApi().resume();
    }

    public void next(){
        mSpotifyAppRemote.getPlayerApi().skipNext();
    }

    public void back(){
        mSpotifyAppRemote.getPlayerApi().skipPrevious();
    }

    public void rafraichir(){
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState ->{
                    final Track track = playerState.track;
                    if (track != null && !playerState.isPaused){
                        Chanson chanson = new Chanson(track.name, new Artiste(track.artist.name), track.album.name);
                        mSpotifyAppRemote.getImagesApi().getImage(track.imageUri).setResultCallback(imgChanson -> {
                            ((PlayerActivity) context).rafraichir(chanson, imgChanson);
                            setvPlayerState(playerState);
                        });
                    }
                });
    }



}
