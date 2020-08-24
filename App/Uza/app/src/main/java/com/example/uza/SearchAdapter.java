package com.example.uza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder > {
    private Context mCtx;
    private List<SearchResults> searchAdapterList;

    public SearchAdapter(Context mCtx, List<SearchResults> searchAdapterList) {
        this.mCtx = mCtx;
        this.searchAdapterList = searchAdapterList;
    }

    @Override
    public SearchAdapter.SearchViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.search_view_design, null);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchAdapter.SearchViewHolder holder, int position) {
        final SearchResults searchResults = searchAdapterList.get(position);

        holder.product_id.setText(searchResults.getProduct_id());
        //holder.seller_name.setText(searchResults.getSeller_name());
        holder.product_name.setText(searchResults.getProduct_name());
        holder.price.setText("Kshs " + searchResults.getPrice());
        holder.percentage_strike.setText(searchResults.getPercentage_strike() + "%");
        int reduction = (Integer.parseInt((searchResults.getPrice()))*(Integer.parseInt(searchResults.getPercentage_strike())+100))/100;

        String reducted = String.valueOf(reduction);
        holder.price_striked.setText("Kshs " + reducted);
        holder.rating.setText(searchResults.getRating());
        holder.category.setText(searchResults.getCategory());

        Picasso.get()
                .load(searchResults.getProduct_image())
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
                intent.putExtra("product_id", searchResults.getProduct_id());
                intent.putExtra("product_name", searchResults.getProduct_name());
                intent.putExtra("product_category", searchResults.getCategory());
                intent.putExtra("product_description", searchResults.getDescription());
                intent.putExtra("product_quantity", searchResults.getQuantity());
                intent.putExtra("product_original_price", searchResults.getOriginal_price());
                intent.putExtra("product_price", searchResults.getPrice());
                intent.putExtra("seller_name", searchResults.getSeller_name());
                intent.putExtra("product_rating", searchResults.getRating());
                intent.putExtra("product_percent", searchResults.getPercentage_strike());
                intent.putExtra("product_image", searchResults.getProduct_image());
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
        return searchAdapterList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView product_id,seller_name,product_name,price,
                price_striked,percentage_strike,rating,category;
        ImageView product_image;
        CardView cardView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.search_id);
            //seller_name = itemView.findViewById(R.id.product_seller_name);
            product_name = itemView.findViewById(R.id.search_product_name);
            price = itemView.findViewById(R.id.search_price);
            price_striked = itemView.findViewById(R.id.search_price_strike);
            percentage_strike = itemView.findViewById(R.id.search_percent_reduction);
            rating = itemView.findViewById(R.id.search_rating);
            category = itemView.findViewById(R.id.search_category);
            cardView = itemView.findViewById(R.id.categories_cardview);

            product_image = itemView.findViewById(R.id.search_image);
            cardView = itemView.findViewById(R.id.search_cardview);
        }
    }
}

