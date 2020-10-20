package com.example.uza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder > {
    private Context mCtx;
    private List<Products> productsList;

    public ProductsAdapter(Context mCtx, List<Products> productsList) {
        this.mCtx = mCtx;
        this.productsList = productsList;
    }

    @Override
    public ProductsAdapter.ProductsViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.products_design_item, null);
        return new ProductsAdapter.ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.ProductsViewHolder holder, int position) {
        final Products products = productsList.get(position);

        holder.product_id.setText(products.getProduct_id());
        holder.seller_name.setText(products.getSeller_name());
        holder.product_name.setText(products.getProduct_name());
        holder.price.setText("Kshs " + products.getPrice());
        holder.percentage_strike.setText(products.getPercentage_strike() + "%");
        int reduction = (Integer.parseInt((products.getPrice()))*(Integer.parseInt(products.getPercentage_strike())+100))/100;

        String reducted = String.valueOf(reduction);
        holder.price_striked.setText("Kshs " + reducted);
        holder.rating.setText(products.getRating());
        holder.category.setText(products.getCategory());

        Picasso.get()
                .load(products.getProduct_image())
                .resize(180,180)
                .centerInside()
                .error(R.drawable.ic_shopping_cart_black_24dp)
                .into(holder.product_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
//                        loading2.setVisibility(View.GONE);
//                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ProductViewActivity.class);
                intent.putExtra("product_id", products.getProduct_id());
                intent.putExtra("product_name", products.getProduct_name());
                intent.putExtra("product_category", products.getCategory());
                intent.putExtra("product_description", products.getDescription());
                intent.putExtra("product_quantity", products.getQuantity());
                intent.putExtra("product_original_price", products.getOriginal_price());
                intent.putExtra("product_price", products.getPrice());
                intent.putExtra("seller_name", products.getSeller_name());
                intent.putExtra("product_rating", products.getRating());
                intent.putExtra("product_percent", products.getPercentage_strike());
                intent.putExtra("product_image", products.getProduct_image());
                mCtx.startActivity(intent);
                ((Activity)mCtx).finish();
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView product_id,seller_name,product_name,price,
                price_striked,percentage_strike,rating,category;
        ImageView product_image;
        CardView cardView;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.product_id);
            seller_name = itemView.findViewById(R.id.product_seller_name);
            product_name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            price_striked = itemView.findViewById(R.id.product_price_strikethrough);
            percentage_strike = itemView.findViewById(R.id.product_percent_reduction);
            rating = itemView.findViewById(R.id.product_rating);
            category = itemView.findViewById(R.id.product_category);
            cardView = itemView.findViewById(R.id.categories_cardview);

            product_image = itemView.findViewById(R.id.product_image);
            cardView = itemView.findViewById(R.id.products_cardview);
        }
    }
}
