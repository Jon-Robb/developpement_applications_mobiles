package com.example.annexe_8_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.SimpleTimeZone;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ListView liste;
    private RequestQueue queue;
    private StringRequest requete;
    private JsonObjectRequest jsor;
    private String url = "https://api.jsonbin.io/v3/b/637056232b3499323bfe110a?meta=false";
    private Vector<Hashtable<String, String>> vecHash;
    Ecouteur ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vecHash = new Vector<>();
        liste = findViewById(R.id.liste);
        queue = Volley.newRequestQueue(this);
        jsor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray tab = response.getJSONArray("articles");
                    for (int i = 0; i < tab.length(); i++){
                        JSONObject article = tab.getJSONObject(i);
                        Hashtable <String,String> hashtable = new Hashtable<>();
                        hashtable.put("pack", article.getString("nom"));
                        hashtable.put("prix", article.getString("prix"));
                        vecHash.add(hashtable);
                        String[] strings = {"pack", "prix"};
                        int[] integers = {R.id.pack, R.id.prix};
                        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, vecHash, R.layout.liste_enfant, strings, integers);
                        System.out.println(vecHash);
                        liste.setAdapter(simpleAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        }, error -> System.out.println(error));

//        requete = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
//            }
//        } , error -> {
//            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            System.out.println(error.toString());
//        });

        queue.add(jsor);
//        queue.add(requete);
        ec = new Ecouteur();
        liste.setOnItemClickListener(ec);

    }

    public class Ecouteur implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this, vecHash.elementAt(i).get("prix"), Toast.LENGTH_LONG).show();
        }
    }


}