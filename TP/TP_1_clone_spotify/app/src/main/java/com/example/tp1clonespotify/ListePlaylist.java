package com.example.tp1clonespotify;

import java.util.Hashtable;
import java.util.Vector;

public class ListePlaylist {

    private Vector<Hashtable<String, String>> vecHash;
    private Vector<Playlist> vecPlaylist;


    public ListePlaylist() {

        Playlist bestOfRadiohead = new Playlist("Best of Radiohead", 42, "3h11m");
        Playlist OkComputer = new Playlist("Ok Computer", 23, "1h32m");
        Playlist KidA = new Playlist("Kid A", 34, "2h5m");

        this.vecPlaylist = new Vector<>();
        this.vecPlaylist.add(bestOfRadiohead);
        this.vecPlaylist.add(OkComputer);
        this.vecPlaylist.add(KidA);

        this.vecHash = new Vector<>();
        for (Playlist playlist : this.vecPlaylist){
            Hashtable hashtable = new Hashtable<>();
            hashtable.put("nom", playlist.getNom());
            hashtable.put("nbChansons", playlist.getNbChansons() + " chansons");
            hashtable.put("duree", "Durée : "+ playlist.getDuree());
            this.vecHash.add(hashtable);
        }

    }

    public Vector<Hashtable<String, String>> getVecHash() {
        return vecHash;
    }
}
