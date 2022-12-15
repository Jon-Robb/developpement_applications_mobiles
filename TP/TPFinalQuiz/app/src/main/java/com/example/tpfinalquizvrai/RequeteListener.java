package com.example.tpfinalquizvrai;

import com.android.volley.Response;

import org.json.JSONObject;

public class RequeteListener implements Response.Listener<JSONObject>{

    private RequeteTermineeListener listener;

    public RequeteListener(RequeteTermineeListener listener){
        this.listener = listener;
    }

    @Override
    public void onResponse(JSONObject response) {
        listener.requeteTerminee(response);
    }
}
