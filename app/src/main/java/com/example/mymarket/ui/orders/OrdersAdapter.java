package com.example.mymarket.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.R;
import com.example.mymarket.model.OrderBriefInfo;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    private ArrayList<OrderBriefInfo> orders;

    public OrdersAdapter(ArrayList<OrderBriefInfo> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        OrderBriefInfo order = orders.get(position);
        holder.textViewOrderId.setText(String.format("%s", order.getId()));
        holder.textViewOrderStatus.setText(order.getOrderStatus());
        holder.textViewOrderPrice.setText(String.format("Сумма: %s", order.getTotalPrice()));
        holder.textViewOrderCreated.setText(String.format("%s %s", order.getCreatedAt().substring(0,10), order.getCreatedAt().substring(11,16)));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewOrderId;
        private TextView textViewOrderStatus;
        private TextView textViewOrderPrice;
        private TextView textViewOrderCreated;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderId = itemView.findViewById(R.id.textViewOrderId);
            textViewOrderStatus = itemView.findViewById(R.id.textViewOrderStatus);
            textViewOrderPrice = itemView.findViewById(R.id.textViewOrderPrice);
            textViewOrderCreated = itemView.findViewById(R.id.textViewOrderCreated);
        }
    }
}
