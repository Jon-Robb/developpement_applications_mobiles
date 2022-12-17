package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Collections;

public class Artistes {

    ArrayList<Artiste> artists;

    public ArrayList<Artiste> getTopArtists(int nb) {
        Collections.shuffle(this.artists);
        ArrayList<Artiste> arrArt = new ArrayList<>();
        for (int i = 0; i < nb; i++){
            arrArt.add(this.artists.get(i));
        }
        return arrArt;
    }


    //     public boolean hasMoreFollowers(Artiste pArtiste){
//
//        for (Artiste artiste : this.artists){
//            if (pArtiste.followers.total < artiste.followers.total){
//                return false;
//            }
//        }
//        return true;
//     }
//
//     public boolean hasMorePopularity(Artiste pArtiste){
//
//         for (Artiste artiste : this.artists){
//             if (pArtiste.popularity < artiste.popularity){
//                 return false;
//             }
//         }
//         return true;
//     }
//
//     public boolean hasGenre(Artiste pArtiste){
//
//        for (Artiste artiste : this.artists){
//            for (String genre1 : artiste.genres){
//                for (String genre2 : pArtiste.genres){
//                    if (genre1.equals(genre2)){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//
//     }

}
