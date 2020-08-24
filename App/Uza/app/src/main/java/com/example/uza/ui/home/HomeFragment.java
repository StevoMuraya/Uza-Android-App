package com.example.uza.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uza.Categories;
import com.example.uza.CategoriesAdapter;
import com.example.uza.Products;
import com.example.uza.ProductsAdapter;
import com.example.uza.R;
import com.example.uza.clsGlobal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;


    private RecyclerView Categories,Products;
    List<Categories> categoryList;
    List<Products>productsList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        pref = getActivity().getSharedPreferences("uza.conf", Context.MODE_PRIVATE);
        editor = pref.edit();

        shared_user_id = pref.getString("shared_user_id","");
        clsGlobal.shared_user_id = shared_user_id;

        shared_fname = pref.getString("shared_fname","");
        clsGlobal.shared_fname = shared_fname;

        shared_lname = pref.getString("shared_lname","");
        clsGlobal.shared_lname = shared_lname;

        shared_email = pref.getString("shared_email","");
        clsGlobal.shared_email = shared_email;

        shared_phone = pref.getString("shared_phone","");
        clsGlobal.shared_phone = shared_phone;

        shared_password = pref.getString("shared_password","");
        clsGlobal.shared_password = shared_password;

        shared_location = pref.getString("shared_location","");
        clsGlobal.shared_location = shared_location;

        shared_date_reg = pref.getString("shared_date_reg","");
        clsGlobal.shared_date_reg = shared_date_reg;

        shared_prof_pic = pref.getString("shared_prof_pic","");
        clsGlobal.shared_prof_pic = shared_prof_pic;

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Categories = view.findViewById(R.id.categories_list);

        Categories.setHasFixedSize(true);
        LinearLayoutManager horizontalScroll = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        Categories.setLayoutManager(horizontalScroll);
        Categories.setNestedScrollingEnabled(false);
        Categories.setFocusable(false);
        categoryList= new ArrayList<>();

        Toast.makeText(getActivity(), "Loading content\nPlease wait...", Toast.LENGTH_LONG).show();

        Products = view.findViewById(R.id.all_products_list);
        Products.setHasFixedSize(true);
        LinearLayoutManager verticalScroll = new GridLayoutManager(getActivity(), 2);
        Products.setLayoutManager(verticalScroll);
        Products.setNestedScrollingEnabled(false);
        Products.setFocusable(false);
        productsList= new ArrayList<>();

        load_categories();
        load_products();
    }


    private void load_categories(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, clsGlobal.ALL_CATEGORIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray categories = new JSONArray(response);
                            int count = 0;

                            for(int i=0;i<categories.length();i++){
                                JSONObject gamesObject = categories.getJSONObject(i);

                                String category_id = gamesObject.getString("category_id");
                                String category_name = gamesObject.getString("category_name");

                                Categories categories1 = new Categories(category_id,category_name);
                                categoryList.add(categories1);

                                CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), categoryList);
                                Categories.setAdapter(adapter);
                                count++;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error Load Games: ", error.toString());
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }


    private void load_products(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, clsGlobal.ALL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            int count = 0;

                            for(int i=0;i<products.length();i++){
                                JSONObject gamesObject = products.getJSONObject(i);

                                String product_id = gamesObject.getString("product_id");
                                String product_name = gamesObject.getString("product_name");
                                String category = gamesObject.getString("category");
                                String description = gamesObject.getString("description");
                                String quantity = gamesObject.getString("quantity");
                                String original_price = gamesObject.getString("original_price");
                                String price = gamesObject.getString("price");
                                String seller_name = gamesObject.getString("seller_name");
                                String rating = gamesObject.getString("rating");
                                String percentage_strike = gamesObject.getString("percentage_strike");
                                String product_image = gamesObject.getString("product_image");
                                String date_added = gamesObject.getString("date_added");

                                Products products1 = new Products(product_id,product_name,category,description,quantity,original_price,price,seller_name,
                                        rating,percentage_strike,product_image,date_added);
                                productsList.add(products1);

                                ProductsAdapter adapter = new ProductsAdapter(getActivity(), productsList);
                                Products.setAdapter(adapter);
                                count++;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Volley_error",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error Load Games: ", error.toString());
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
