package com.example.uza;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOutActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private RecyclerView checkout_recycler;
    private List<Cart> cartList;
    private int total_cash,count,tax;
    private TextView total_checkout,checkout_count,tax_checkout,user_name,user_location,user_details;
    private LikeButton mpesa,credit_card,cash;
    private int payment = 0;
    private Button proceed;
    private ImageView mpesa_check,card_check,cash_check;
    private TextView select_payment,checkout_cost_no_shipping;


    private int total_cost;
    private String order_id_special;
    private int total_items = 0,total_items_check = 1;
    private RelativeLayout loading_screen;
    private ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.getSupportActionBar().hide();

        try {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        pref = this.getSharedPreferences("uza.conf", Context.MODE_PRIVATE);
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

        checkout_recycler = findViewById(R.id.checkout_recycler);
        total_checkout = findViewById(R.id.checkout_total_amount);
        checkout_count = findViewById(R.id.checkout_count);
        tax_checkout = findViewById(R.id.tax_checkout);
        user_name = findViewById(R.id.checkout_username);
        user_location = findViewById(R.id.checkout_location);
        user_details = findViewById(R.id.checkout_details);
        mpesa = findViewById(R.id.checkout_mpesa);
        cash = findViewById(R.id.checkout_cash_on_delivery);
        credit_card = findViewById(R.id.checkout_credit_card);
        proceed = findViewById(R.id.checkout_proceed);
        mpesa_check = findViewById(R.id.mpesa_check);
        cash_check = findViewById(R.id.cash_check);
        card_check = findViewById(R.id.card_check);
        select_payment = findViewById(R.id.select_payment);
        checkout_cost_no_shipping  = findViewById(R.id.checkout_cost_no_shipping);

        loading = findViewById(R.id.cash_loading);
        loading_screen = findViewById(R.id.cash_loading_screen);

        user_name.setText(shared_fname + " " + shared_lname);
        user_location.setText(shared_location);
        user_details.setText(shared_email + " | " + shared_phone);

        checkout_recycler.setHasFixedSize(true);
        LinearLayoutManager horizontalScroll = new LinearLayoutManager(CheckOutActivity.this,LinearLayoutManager.VERTICAL,false);
        checkout_recycler.setLayoutManager(horizontalScroll);
        checkout_recycler.setNestedScrollingEnabled(false);
        checkout_recycler.setFocusable(false);
        cartList= new ArrayList<>();
        load_cart(shared_user_id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProceedButton();
            }
        });

        mpesa.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                credit_card.setLiked(false);
                cash.setLiked(false);
                payment = 1;
                card_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.VISIBLE);
                select_payment.setVisibility(View.INVISIBLE);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                payment = 0;
                card_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.INVISIBLE);
            }
        });
        credit_card.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mpesa.setLiked(false);
                cash.setLiked(false);
                payment = 2;
                card_check.setVisibility(View.VISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.INVISIBLE);
                select_payment.setVisibility(View.INVISIBLE);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                payment = 0;
                card_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.INVISIBLE);
            }
        });

        cash.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mpesa.setLiked(false);
                credit_card.setLiked(false);
                payment = 3;
                card_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.VISIBLE);
                select_payment.setVisibility(View.INVISIBLE);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                payment = 0;
                card_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                mpesa_check.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CheckOutActivity.this,ActivityUzaHomePage.class));
        finish();
    }

    private void onProceedButton(){

        if (payment == 0){
            mpesa.performClick();
            payment = 0;
            mpesa_check.setVisibility(View.VISIBLE);
            new CountDownTimer(300, 4000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    credit_card.performClick();
                    payment = 0;
                    card_check.setVisibility(View.VISIBLE);

                    new CountDownTimer(300, 4000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            cash.performClick();
                            payment = 0;
                            cash_check.setVisibility(View.VISIBLE);
                            new CountDownTimer(300, 4000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    select_payment.setVisibility(View.VISIBLE);
                                    mpesa.setLiked(false);
                                    mpesa_check.setVisibility(View.INVISIBLE);

                                    new CountDownTimer(300, 4000) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }
                                        @Override
                                        public void onFinish() {
                                            credit_card.setLiked(false);
                                            card_check.setVisibility(View.INVISIBLE);

                                            new CountDownTimer(300, 4000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                }
                                                @Override
                                                public void onFinish() {
                                                    cash.setLiked(false);
                                                    cash_check.setVisibility(View.INVISIBLE);
                                                    payment = 0;
                                                }
                                            }.start();
                                        }
                                    }.start();
                                }
                            }.start();
                        }
                    }.start();
                }
            }.start();
        }else if (payment == 1){
            startActivity(new Intent(CheckOutActivity.this, MpesaPaymentActivity.class));
            finish();
        }else if (payment == 2){
            startActivity(new Intent(CheckOutActivity.this, CreditCardPaymentActivity.class));
            finish();
        }else if (payment == 3){
            Toast.makeText(CheckOutActivity.this, String.valueOf(total_cost), Toast.LENGTH_SHORT).show();
            if ((total_cash-300)<=1000){
                Prompt_Cash_On_Delivery(CheckOutActivity.this).show();
            }else{
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yy:yy:MM:dd:HH:mm:ss");
                String formatDate = dateFormat.format(calendar.getTime());
                Log.d("Current_date_time",formatDate);
                order_id_special = dateFormat.format(calendar.getTime());

                proceed.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading_screen.setVisibility(View.VISIBLE);
                loading_screen.animate().alpha(0.5f).setDuration(500);
                String cost = String.valueOf(total_cost+300);
                paymentProcessing(shared_user_id,cost,"Cash On Delivery","Cash On Delivery");
                performPurchase(shared_user_id);
            }
        }
    }


    private void load_cart(final String user_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.ALL_CART_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("anyText1",response);
                        try {
                            JSONArray cart = new JSONArray(response);
                            count =0;
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

                                Cart cart1 = new Cart(cart_id,user_id,product_id,product_name,category,description,quantity,original_price,price,
                                        seller_name,rating,percentage_strike,product_image,date_added);
                                cartList.add(cart1);



                                total_cost = total_cost + (Integer.parseInt(quantity) * Integer.parseInt(price));
                                total_items = total_items + 1;

                                tax = tax + (16*Integer.parseInt(price))/100;
                                total_cash = total_cash + (Integer.parseInt(quantity) * Integer.parseInt(price));
                                count = count + Integer.parseInt(quantity);

                                checkout_count.setText(count + " items");
                                checkout_cost_no_shipping.setText("Kshs: "+total_cash);
                                total_checkout.setText("Kshs "+ (total_cash+300));
                                tax_checkout.setText("Kshs "+tax);

                                CheckoutAdapter adapter = new CheckoutAdapter(CheckOutActivity.this, cartList);
                                checkout_recycler.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CheckOutActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckOutActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueus = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueus.add(stringRequest);
    }

    public AlertDialog.Builder Prompt_Cash_On_Delivery(final Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("Cash on deliveries requires orders above Kshs 1,000\n" +
                "Shipping charges excluded");
        mpesa.setLiked(false);
        credit_card.setLiked(false);
        cash.setLiked(false);
        mpesa_check.setVisibility(View.INVISIBLE);
        card_check.setVisibility(View.INVISIBLE);
        cash_check.setVisibility(View.INVISIBLE);
        payment = 0;

        builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mpesa.setLiked(false);
                credit_card.setLiked(false);
                cash.setLiked(false);
                mpesa_check.setVisibility(View.INVISIBLE);
                card_check.setVisibility(View.INVISIBLE);
                cash_check.setVisibility(View.INVISIBLE);
                payment = 0;
            }
        });

        return builder;
    }


    private void performPurchase(final String user_id){
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

                                total_cost = total_cost + (Integer.parseInt(quantity) * Integer.parseInt(price));

                                purchaseProcessing(cart_id,product_id,shared_user_id,product_name,quantity,price,order_id_special);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CheckOutActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            proceed.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckOutActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        proceed.setVisibility(View.VISIBLE);
                        loading_screen.animate().alpha(0f).setDuration(500);
                        loading_screen.setVisibility(View.GONE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueus.add(stringRequest);
    }

    private void paymentProcessing(final String user_idx,final String payment_amountx,final String payment_methodx, final String payment_codex){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.UPDATE_PAYMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated")){

                        }else{
                            new CountDownTimer(2500, 4000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    Toast.makeText(CheckOutActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    proceed.setVisibility(View.VISIBLE);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading_screen.setVisibility(View.GONE);
                                }
                            }.start();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {

                                Toast.makeText(CheckOutActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CheckOutActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                proceed.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading_screen.setVisibility(View.GONE);
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id",user_idx);
                params.put("payment_amount",payment_amountx);
                params.put("payment_method",payment_methodx);
                params.put("payment_code",payment_codex);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueue.add(stringRequest);
    }


    private void purchaseProcessing(final String cart_idx,final String product_idx,final String user_idx, final String product_namex, final String product_quantityx, final String pricex, final String order_codex){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.UPDATE_PURCHASES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated2")){
                            int quantityx = Integer.parseInt(product_quantityx);
                            check_quantity(product_idx,quantityx,cart_idx);
                        }else{
                            new CountDownTimer(2500, 4000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    Toast.makeText(CheckOutActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    proceed.setVisibility(View.VISIBLE);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading_screen.setVisibility(View.GONE);
                                }
                            }.start();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {

                                Toast.makeText(CheckOutActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CheckOutActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                proceed.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading_screen.setVisibility(View.GONE);
                            }
                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("cart_id",cart_idx);
                params.put("product_id",product_idx);
                params.put("user_id",user_idx);
                params.put("product_name",product_namex);
                params.put("product_quantity",product_quantityx);
                params.put("price",pricex);
                params.put("order_code",order_codex);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueue.add(stringRequest);
    }

    private void check_quantity(final String product_id,final int new_quantity,final String cart_idx){
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

                                int final_quantity = (quantity_i - new_quantity);
                                String quantityx = String.valueOf(final_quantity);
                                updateProductQuantity(product_id,quantityx);
                                updateUsersCart(shared_user_id,cart_idx);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CheckOutActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("anyText1",e.toString());
                            loading.setVisibility(View.GONE);
                            proceed.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckOutActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        proceed.setVisibility(View.VISIBLE);
                        loading_screen.animate().alpha(0f).setDuration(500);
                        loading_screen.setVisibility(View.GONE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_id",product_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueus.add(stringRequest);
    }
    private void updateProductQuantity(final String product_id,final String quantity){
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.UPDATE_PRODUCT_QUANTITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated Product")){
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {

                                }
                            }.start();

                        }else{
                            new CountDownTimer(1500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    Toast.makeText(CheckOutActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    proceed.setVisibility(View.VISIBLE);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading_screen.setVisibility(View.GONE);
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
                                Toast.makeText(CheckOutActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CheckOutActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                proceed.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading_screen.setVisibility(View.GONE);
                            }
                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("quantity",quantity);
                params.put("product_id",product_id);

                String data = params.toString();
                Log.d("Data_sent",data);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueue.add(stringRequest);
    }
    private void updateUsersCart(final String user_id,final String cart_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.UPDATE_PURCHASE_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Updated Cart")){

                            if (total_items_check == total_items){
                                loading.setVisibility(View.GONE);
                                proceed.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading_screen.setVisibility(View.GONE);
                                startActivity(new Intent(CheckOutActivity.this, OrderConfirmationActivity.class));
                                finish();
                            }else{
                                total_items_check  = total_items_check + 1;
                            }

                            //Toast.makeText(MpesaPaymentActivity.this, "total_items_check" + total_items_check, Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(CheckOutActivity.this, response, Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            proceed.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckOutActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                        Toast.makeText(CheckOutActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        proceed.setVisibility(View.VISIBLE);
                        loading_screen.animate().alpha(0f).setDuration(500);
                        loading_screen.setVisibility(View.GONE);
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

        RequestQueue requestQueue = Volley.newRequestQueue(CheckOutActivity.this);
        requestQueue.add(stringRequest);
    }


}
