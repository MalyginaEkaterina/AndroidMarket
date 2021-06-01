package com.example.mymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymarket.model.LoginRequest;
import com.example.mymarket.model.SignUpRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrActivity extends AppCompatActivity {

    private EditText editTextRegLogin;
    private EditText editTextRegPassword;
    private EditText editTextFio;
    private EditText editTextPhone;
    private EditText editTextEmail;
    RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        editTextRegLogin = findViewById(R.id.editTextRegLogin);
        editTextRegPassword = findViewById(R.id.editTextRegPassword);
        editTextFio = findViewById(R.id.editTextFio);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public void sendRegRequest(View view) {
        if (editTextRegLogin.getText().toString().isEmpty() || editTextRegPassword.getText().toString().isEmpty()) {
            Toast toast = new Toast(this);
            toast.setText(getResources().getString(R.string.empty_login_pass));
            toast.show();
        } else {
            try {
                String url = "http://10.0.2.2:8188/auth/register/";
                SignUpRequest req = new SignUpRequest(editTextRegLogin.getText().toString(), editTextRegPassword.getText().toString(),
                        editTextFio.getText().toString(), editTextPhone.getText().toString(), editTextEmail.getText().toString());
                Gson gson = new Gson();
                JSONObject reqJson = new JSONObject(gson.toJson(req));
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = new Toast(RegistrActivity.this);
                        toast.setText(getResources().getString(R.string.reg_success));
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(RegistrActivity.this, AuthActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast toast = new Toast(RegistrActivity.this);
                        toast.setText(getResources().getString(R.string.reg_not_success));
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return reqJson.toString().getBytes();
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }
                };
                mRequestQueue.add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}