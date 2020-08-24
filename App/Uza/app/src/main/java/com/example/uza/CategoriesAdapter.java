package com.example.uza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder > {
    private Context mCtx;
    private List<Categories> categoriesList;

    public CategoriesAdapter(Context mCtx, List<Categories> categoriesList) {
        this.mCtx = mCtx;
        this.categoriesList = categoriesList;
    }

    @Override
    public CategoriesAdapter.CategoriesViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.categories_design_item, null);
        return new CategoriesAdapter.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, int position) {
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

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView category_id,category_name;
        CardView cardView;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            category_id = itemView.findViewById(R.id.category_id);
            category_name = itemView.findViewById(R.id.category_name);
            cardView = itemView.findViewById(R.id.categories_cardview);
        }
    }
}
