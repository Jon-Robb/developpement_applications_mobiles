package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private String url = "https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb";
    private JsonObjectRequest requete;
    private RequestsSingleton instance;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        instance = RequestsSingleton.getInstance(this);
        requete = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    JSONArray arr = response.getJSONArray("genres");
//                    System.out.println(arr);
                    Gson gson = new GsonBuilder().create();
                    Artiste artiste = gson.fromJson(String.valueOf(response), Artiste.class);
                    System.out.println(artiste.name);
                    System.out.println(artiste.genres);
                    System.out.println(artiste.followers.total);
                    System.out.println(artiste.popularity);
                    for (Image image : artiste.images){
                        System.out.println(image.url);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        }, error -> error.printStackTrace()){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = instance.getToken();
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        instance.addToRequestQueue(requete);

    }

}