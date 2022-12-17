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
        this.artisteIDs.add("0k17h0D3J5VfsdmQ1iZtE9");
        this.artisteIDs.add("0L8ExT028jH3ddEcZwqJJ5");
        this.artisteIDs.add("3k0HFz1mMtmsaWYsX75MiW");
        this.artisteIDs.add("2cdnxq3gRcJudFLmWhDTQp");
        this.artisteIDs.add("7dGJo4pcD2V6oG8kP0tJRR");
        this.artisteIDs.add("2o5jDhtHVPhrJdv3cEQ99Z");
        this.artisteIDs.add("7sk1rfSVKopRQf1fxSJLTq");

    }

    public String generateTwoArtistsUrl(){

        Collections.shuffle(this.artisteIDs);

        return this.https + this.artisteIDs.get(0) + "%2C" + this.artisteIDs.get(1);

    }

}
