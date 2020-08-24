package com.example.uza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder > {
    TextView product_name,price,category,cart_quantity,sub_total;

    private Context mCtx;
    private List<Cart> cartList;

    public CheckoutAdapter(Context mCtx, List<Cart> cartList) {
        this.mCtx = mCtx;
        this.cartList = cartList;
    }

    @Override
    public CheckoutAdapter.CheckoutViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.checkout_design_view, null);
        return new CheckoutAdapter.CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckoutAdapter.CheckoutViewHolder holder, int position) {
        final Cart cart = cartList.get(position);

        product_name.setText(cart.getProduct_name());
        category.setText(cart.getCategory());
        cart_quantity.setText(cart.getQuantity()+" unit(s)");
        price.setText("Kshs " + cart.getPrice());

        String sub_tot = String.valueOf((Integer.parseInt(cart.getPrice())*Integer.parseInt(cart.getQuantity())));
        sub_total.setText("Kshs "+sub_tot);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CheckoutViewHolder extends RecyclerView.ViewHolder {

        public CheckoutViewHolder(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.checkout_item_name);
            category = itemView.findViewById(R.id.checkout_item_category);
            price = itemView.findViewById(R.id.checkout_item_price);
            cart_quantity = itemView.findViewById(R.id.checkout_item_quantity);
            sub_total = itemView.findViewById(R.id.checkout_subtotal);
        }
    }
}
