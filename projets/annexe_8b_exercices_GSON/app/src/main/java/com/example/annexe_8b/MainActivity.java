package com.example.annexe_8b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TextView header, items, nulls, nolabels;
    private String url = "https://api.jsonbin.io/v3/b/6374dac065b57a31e6b93755?meta=false";
    private JsonObjectRequest requestA;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        items = findViewById(R.id.items);
        nulls = findViewById(R.id.nulls);
        nolabels = findViewById(R.id.nolabels);


        queue = Volley.newRequestQueue(this);
        requestA = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject h = response.getJSONObject("menu");
                    header.setText(h.getString("header"));
                    JSONArray i = h.getJSONArray("items");
                    items.setText(String.valueOf(i.length()));
                    int count = 0;
                    int countLabels = 0;
                    for (int j = 0; j < i.length(); j++){
                        if (i.isNull(j)){
                            ++count;
                        }
                        else if (!i.getJSONObject(j).has("label")){
                            ++countLabels;
                        }
                    }
                    nulls.setText(String.valueOf(count));
                    nolabels.setText(String.valueOf(countLabels));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, error -> System.out.println("Erreur! : " + error));

        queue.add(requestA);

    }
}