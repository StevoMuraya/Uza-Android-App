package com.example.uza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder > {
    TextView order_number, d_date, items_count;

    private Context mCtx;
    private List<Orders> ordersList;

    public OrdersAdapter(Context mCtx, List<Orders> ordersList) {
        this.mCtx = mCtx;
        this.ordersList = ordersList;
    }

    @Override
    public OrdersAdapter.OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_orders_card_design, null);
        return new OrdersAdapter.OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrdersAdapter.OrdersViewHolder holder, int position) {
        final Orders orders = ordersList.get(position);

        order_number.setText(orders.getOrder_number());
        items_count.setText(orders.getItems()+" item(s)");
        d_date.setText(orders.getD_date());

    }


    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder {

        public OrdersViewHolder(View itemView) {
            super(itemView);
            order_number = itemView.findViewById(R.id.order_number);
            items_count = itemView.findViewById(R.id.order_items);
            d_date = itemView.findViewById(R.id.order_date_time);
        }
    }
}
