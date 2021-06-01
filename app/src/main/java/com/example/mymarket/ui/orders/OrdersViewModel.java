package com.example.mymarket.ui.orders;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymarket.model.OrderBriefInfo;
import com.example.mymarket.model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<OrderBriefInfo>> orders;
    RequestQueue mRequestQueue;

    public OrdersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<OrderBriefInfo>> getOrders() {
        if (orders == null) {
            mRequestQueue = Volley.newRequestQueue(getApplication());
            orders = new MutableLiveData<>();
            String url = "http://10.0.2.2:8192/order/";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Gson gson = new Gson();
                    orders.setValue(gson.fromJson(response.toString(), new TypeToken<List<OrderBriefInfo>>(){}.getType()));
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
        return orders;
    }
}