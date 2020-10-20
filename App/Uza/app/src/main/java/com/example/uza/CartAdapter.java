package com.example.uza;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder > {
    TextView product_name,price,
            percentage_strike,category,cart_quantity;
    ImageView product_image;
    CardView plus_1_cart,minus_1_cart;
    Button remove;
    RelativeLayout loading_screen;
    ProgressBar loading;

    private Context mCtx;
    private List<Cart> cartList;

    public CartAdapter(Context mCtx, List<Cart> cartList) {
        this.mCtx = mCtx;
        this.cartList = cartList;
    }

    @Override
    public CartAdapter.CartViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_design_view, null);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.CartViewHolder holder, int position) {
        final Cart cart = cartList.get(position);
        product_name.setText(cart.getProduct_name());
        price.setText("Kshs " + cart.getPrice());
        percentage_strike.setText(cart.getPercentage_strike() + "%");
        category.setText(cart.getCategory());
        cart_quantity.setText(cart.getQuantity());

        Picasso.get()
                .load(cart.getProduct_image())
                .resize(180,180)
                .centerInside()
                .error(R.drawable.ic_shopping_cart_black_24dp)
                .into(product_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
//                        loading2.setVisibility(View.GONE);
//                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prompt_Remove(mCtx,cart.getProduct_name(),cart.getCart_id()).show();
            }
        });

        plus_1_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Please wait", Toast.LENGTH_SHORT).show();
                check_stock(cart.getProduct_id(),((Integer.parseInt(cart.getQuantity()))+1),cart.getCart_id());
                remove.setEnabled(false);
                plus_1_cart.setEnabled(false);
                minus_1_cart.setEnabled(false);
            }
        });
        minus_1_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qua = Integer.parseInt(cart.getQuantity());
                if (qua<=1) {
                    Prompt_Remove(mCtx,cart.getProduct_name(),cart.getCart_id()).show();
                }else{
                    int update_q = Integer.parseInt(cart.getQuantity())-1;
                    String quantity_new = String.valueOf(update_q);
                    remove.setEnabled(false);
                    Toast.makeText(mCtx, "Please wait", Toast.LENGTH_SHORT).show();
                    plus_1_cart.setEnabled(false);
                    minus_1_cart.setEnabled(false);
                    updateQuantity(clsGlobal.shared_user_id, cart.getCart_id(), quantity_new);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        public CartViewHolder(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.cart_product_name);
            price = itemView.findViewById(R.id.cart_price);
            percentage_strike = itemView.findViewById(R.id.cart_percent);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);
            category = itemView.findViewById(R.id.cart_category);
            remove = itemView.findViewById(R.id.cart_remove_item);
            loading_screen = itemView.findViewById(R.id.cart_design_loading_screen);
            loading = itemView.findViewById(R.id.cart_loading);

            product_image = itemView.findViewById(R.id.cart_image_view);
            plus_1_cart = itemView.findViewById(R.id.plus_1_cart);
            minus_1_cart = itemView.findViewById(R.id.minus_1_cart);
        }
    }
    private void updateQuantity(final String user_id,final String cart_id,final String quantity){
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.UPDATE_QUANTITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated")){
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    remove.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    plus_1_cart.setEnabled(true);
                                    minus_1_cart.setEnabled(true);
                                    //mCtx.startActivity(new Intent(mCtx,ActivityUzaHomePage.class));
                                    ((Activity)mCtx).recreate();
                                }
                            }.start();

                        }else{
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    remove.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    plus_1_cart.setEnabled(true);
                                    minus_1_cart.setEnabled(true);
                                    Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
                                }
                            }.start();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new CountDownTimer(1500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                remove.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                plus_1_cart.setEnabled(true);
                                minus_1_cart.setEnabled(true);
                                Toast.makeText(mCtx, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(mCtx, "Please try again later", Toast.LENGTH_SHORT).show();
                            }
                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("quantity",quantity);
                params.put("cart_id",cart_id);
                params.put("user_id",user_id);

                String data = params.toString();
                Log.d("Data_sent",data);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(stringRequest);
    }


    private void removeProduct(final String user_id,final String cart_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.REMOVE_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated")){
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    remove.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    plus_1_cart.setEnabled(true);
                                    minus_1_cart.setEnabled(true);
                                    //mCtx.startActivity(new Intent(mCtx,ActivityUzaHomePage.class));
                                    ((Activity)mCtx).recreate();
                                }
                            }.start();

                        }else{
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    remove.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    plus_1_cart.setEnabled(true);
                                    minus_1_cart.setEnabled(true);
                                    Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
                                }
                            }.start();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new CountDownTimer(1500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                remove.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                plus_1_cart.setEnabled(true);
                                minus_1_cart.setEnabled(true);
                                Toast.makeText(mCtx, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(mCtx, "Please try again later", Toast.LENGTH_SHORT).show();
                            }
                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("cart_id",cart_id);
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(stringRequest);
    }
    private void check_stock(final String product_id,final int required,final String cart_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.CHECK_CART_QUANTITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray cartz = new JSONArray(response);

                            for(int i=0;i<cartz.length();i++){
                                JSONObject gamesObject = cartz.getJSONObject(i);

                                String quantity = gamesObject.getString("quantity");
                                int quantity_i = Integer.parseInt(quantity);

                                if (quantity_i >= required){
                                    //Toast.makeText(mCtx, "Product is in stock", Toast.LENGTH_SHORT).show();
                                    String newReq = String.valueOf(required);
                                    updateQuantity(clsGlobal.shared_user_id,cart_id,newReq);
                                }else{
                                    remove.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    plus_1_cart.setEnabled(true);
                                    minus_1_cart.setEnabled(true);
                                    Prompt_Stock(mCtx).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(mCtx, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("anyText1",e.toString());
//                    Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mCtx, "Error 2", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_id",product_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(mCtx);
        requestQueus.add(stringRequest);
    }

    public AlertDialog.Builder Prompt_Stock(final Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("The product is currently limited in stock.\nWe cannot be able to add more of the product to your cart. " +
                "Please consider buying the selected quantity or remove the product from your cart");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }

    public AlertDialog.Builder Prompt_Remove(final Context c, String name, final String cart_id_x) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Remove "+name);
        builder.setMessage("Are you sure you want to remove " + name + " from your cart!");

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove.setEnabled(false);
                Toast.makeText(mCtx, "Please wait", Toast.LENGTH_SHORT).show();
                plus_1_cart.setEnabled(false);
                minus_1_cart.setEnabled(false);
                removeProduct(clsGlobal.shared_user_id,cart_id_x);
            }
        });

        return builder;
    }
}
