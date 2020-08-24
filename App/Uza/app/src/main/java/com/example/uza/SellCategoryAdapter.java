package com.example.uza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SellCategoryAdapter extends RecyclerView.Adapter<SellCategoryAdapter.SellCategoryViewHolder > {
    private Context mCtx;
    private List<Categories> categoriesList;

    public SellCategoryAdapter(Context mCtx, List<Categories> categoriesList) {
        this.mCtx = mCtx;
        this.categoriesList = categoriesList;
    }

    @Override
    public SellCategoryAdapter.SellCategoryViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.sell_category_design, null);
        return new SellCategoryAdapter.SellCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SellCategoryAdapter.SellCategoryViewHolder holder, int position) {
        final Categories categories = categoriesList.get(position);

        holder.category_id.setText(categories.getCategory_id());
        holder.category_name.setText(categories.getCategory_name());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mCtx, Departments.class);
//                intent.putExtra("hospital_id", hospital_id);
//                intent.putExtra("hosp_name", hospitals.getHospital_name());
//                mCtx.startActivity(intent);
//                ((Activity)mCtx).finish();
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
        return categoriesList.size();
    }

    class SellCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView category_id,category_name;
        CardView cardView;

        public SellCategoryViewHolder(View itemView) {
            super(itemView);
            category_id = itemView.findViewById(R.id.sell_category_id);
            category_name = itemView.findViewById(R.id.sell_category_name);
            cardView = itemView.findViewById(R.id.sell_categories_cardview);
        }
    }
}
