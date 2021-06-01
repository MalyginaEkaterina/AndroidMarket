package com.example.mymarket.ui.profile;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymarket.model.UserInfo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileViewModel extends AndroidViewModel {

    private MutableLiveData<UserInfo> userInfo;
    RequestQueue mRequestQueue;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<UserInfo> getUserInfo() {
        if (userInfo == null) {
            mRequestQueue = Volley.newRequestQueue(getApplication());
            userInfo = new MutableLiveData<>();
            String url = "http://10.0.2.2:8188/auth/user_info/";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    userInfo.setValue(gson.fromJson(response.toString(), UserInfo.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                    String token = preferences.getString("token", "");
                    headers.put("Authorization", token);
                    return headers;
                }
            };
            mRequestQueue.add(request);
        }
        return userInfo;
    }
}