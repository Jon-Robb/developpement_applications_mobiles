package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Collections;

public class UrlGenerator{

    private String https = "https://api.spotify.com/v1/artists?ids=";
    private ArrayList<String> artisteIDs;

    public UrlGenerator(){
        this.artisteIDs = new ArrayList<>();
        this.artisteIDs.add("4Z8W4fKeB5YxbusRsdQVPb");
        this.artisteIDs.add("0C0XlULifJtAgn6ZNCW2eu");
        this.artisteIDs.add("12Chz98pHFMPJEknJQMWvI");
    }

    public String generateTwoArtistsUrl(){

        Collections.shuffle(this.artisteIDs);

        return this.https + this.artisteIDs.get(0) + "%2C" + this.artisteIDs.get(1);

    }

}
