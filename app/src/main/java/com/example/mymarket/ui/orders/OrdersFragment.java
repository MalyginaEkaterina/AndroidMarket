package com.example.mymarket.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.R;
import com.example.mymarket.model.OrderBriefInfo;
import com.example.mymarket.model.UserInfo;
import com.example.mymarket.ui.profile.ProfileViewModel;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    private OrdersViewModel ordersViewModel;
    private RecyclerView recyclerViewOrders;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        ordersViewModel = new ViewModelProvider(getActivity()).get(OrdersViewModel.class);
        ordersViewModel.getOrders().observe(getViewLifecycleOwner(), new Observer<ArrayList<OrderBriefInfo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<OrderBriefInfo> orders) {
                OrdersAdapter adapter = new OrdersAdapter(orders);
                recyclerViewOrders.setAdapter(adapter);
            }
        });
        return view;
    }
}