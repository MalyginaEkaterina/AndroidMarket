package com.example.mymarket.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymarket.AuthActivity;
import com.example.mymarket.MainActivity;
import com.example.mymarket.R;
import com.example.mymarket.model.LoginRequest;
import com.example.mymarket.model.UserInfo;
import com.example.mymarket.ui.home.HomeViewModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private TextView textViewFio;
    private TextView textViewPhone;
    private TextView textViewEmail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewFio = view.findViewById(R.id.textViewFio);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewEmail = view.findViewById(R.id.textViewEmail);

        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        profileViewModel.getUserInfo().observe(getViewLifecycleOwner(), new Observer<UserInfo>() {
            @Override
            public void onChanged(@Nullable UserInfo userInfo) {
                if (userInfo != null) {
                    textViewFio.setText(userInfo.getFio());
                    textViewPhone.setText(userInfo.getPhone());
                    textViewEmail.setText(userInfo.getEmail());
                }
            }
        });
        return view;
    }
}