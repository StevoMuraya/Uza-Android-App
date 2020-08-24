package com.example.uza.ui.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uza.Cart;
import com.example.uza.CartAdapter;
import com.example.uza.CheckOutActivity;
import com.example.uza.Products;
import com.example.uza.ProductsAdapter;
import com.example.uza.R;
import com.example.uza.SearchAdapter;
import com.example.uza.SearchResults;
import com.example.uza.SearchResultsActivity;
import com.example.uza.UserLoginActivity;
import com.example.uza.clsGlobal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private RecyclerView cart_recycler;
    private List<Cart> cartList;
    private int cost = 0;
    private CardView cost_card;
    private Button checkout;
    private RelativeLayout no_data;

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

        if (!(shared_user_id.equals(""))){
            View root = inflater.inflate(R.layout.fragment_cart, container, false);
            return root;
        }else{
            Toast.makeText(getActivity(), "You are required to log in or register to access your cart", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), UserLoginActivity.class));
            getActivity().finish();
            return null;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cart_recycler = view.findViewById(R.id.cart_recycler);
        cost_card = view.findViewById(R.id.total_cost_card);

        no_data = view.findViewById(R.id.cart_without_info);
        checkout = view.findViewById(R.id.cart_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Please wait...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), CheckOutActivity.class));
                getActivity().finish();
            }
        });


        cart_recycler.setHasFixedSize(true);
        LinearLayoutManager horizontalScroll = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        cart_recycler.setLayoutManager(horizontalScroll);
        cart_recycler.setNestedScrollingEnabled(false);
        cart_recycler.setFocusable(false);
        cartList= new ArrayList<>();

        load_cart(shared_user_id);
    }

    private void load_cart(final String user_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.ALL_CART_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText1",response);
                        try {
                            JSONArray cart = new JSONArray(response);

                            for(int i=0;i<cart.length();i++){
                                JSONObject gamesObject = cart.getJSONObject(i);

                                String cart_id = gamesObject.getString("cart_id");
                                String user_id = gamesObject.getString("user_id");
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

                                //Toast.makeText(getActivity(), "ID: "+product_id, Toast.LENGTH_SHORT).show();
                                Cart cart1 = new Cart(cart_id,user_id,product_id,product_name,category,description,quantity,original_price,price,
                                        seller_name,rating,percentage_strike,product_image,date_added);
                                cartList.add(cart1);

                                CartAdapter adapter = new CartAdapter(getActivity(), cartList);
                                cart_recycler.setAdapter(adapter);
                                cost = cost + (Integer.parseInt(price)*Integer.parseInt(quantity));
                            }
                            if (cost <= 0){
                                cart_recycler.setVisibility(View.GONE);
                                Animation push_down_in  = AnimationUtils.loadAnimation(getContext(),R.anim.push_down_in);
                                no_data.setVisibility(View.VISIBLE);
                                cost_card.setAnimation(push_down_in);
                                checkout.setVisibility(View.GONE);
                            }

                            String cost_s = String.valueOf(cost);
                            Animation push_up_in  = AnimationUtils.loadAnimation(getActivity().getBaseContext(),R.anim.push_up_in);
                            cost_card.setVisibility(View.VISIBLE);
                            cost_card.setAnimation(push_up_in);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error 1", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error 2", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(getActivity());
        requestQueus.add(stringRequest);
    }
}
