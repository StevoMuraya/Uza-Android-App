package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class CreditCardPaymentActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private Button credit_card_submit;
    private EditText secret_number, expiry_date, card_number;
    private TextView total_amount_card;

    private RelativeLayout loading_screen;
    private ProgressBar loading;

    private int total_cost;
    private String order_id_special;
    private int total_items = 0,total_items_check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payment);

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

        credit_card_submit = findViewById(R.id.credit_card_submit);
        secret_number = findViewById(R.id.card_secret_number);
        card_number = findViewById(R.id.card_number);
        expiry_date = findViewById(R.id.code_expiry_date);
        total_amount_card = findViewById(R.id.total_amount_card);

        loading = findViewById(R.id.credit_card_loading);
        loading_screen = findViewById(R.id.credit_card_loading_screen);

        load_cart(shared_user_id);

        credit_card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yy:yy:MM:dd:HH:mm:ss");
                String formatDate = dateFormat.format(calendar.getTime());
                Log.d("Current_date_time",formatDate);
                order_id_special = dateFormat.format(calendar.getTime());
                attempt_submit();
            }
        });
    }


    public void attempt_submit(){
        String card_number_z  = card_number.getText().toString();
        String expiry_z       = expiry_date.getText().toString();
        String secret_z       = secret_number.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(card_number_z)) {
            card_number.setError("Code number is required to proceed");
            focusView = card_number;
            cancel = true;
        }else if (!isCodeLongEnough(card_number_z)) {
            card_number.setError("Card number is too short");
            focusView = card_number;
            cancel = true;
        }else if (TextUtils.isEmpty(expiry_z)) {
            expiry_date.setError("Enter expiry date");
            focusView = expiry_date;
            cancel = true;
        }else if (TextUtils.isEmpty(secret_z)) {
            secret_number.setError("Enter card secret number");
            focusView = secret_number;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }else{
            credit_card_submit.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            loading_screen.setVisibility(View.VISIBLE);
            loading_screen.animate().alpha(0.5f).setDuration(500);
            String cost = String.valueOf(total_cost+300);
            paymentProcessing(shared_user_id,cost,"Credit | Debit Card",card_number_z);
            performPurchase(shared_user_id);
        }
    }
    private boolean isCodeLongEnough(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 15;
    }




    private void load_cart(final String user_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.ALL_CART_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("anyText1",response);
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
                                total_items = total_items + 1;

                            }
                            total_amount_card.setText("KShs "+(total_cost+300));
                            //Toast.makeText(MpesaPaymentActivity.this, "total_items"+total_items, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreditCardPaymentActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreditCardPaymentActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(CreditCardPaymentActivity.this);
        requestQueus.add(stringRequest);
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
                            Toast.makeText(CreditCardPaymentActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            credit_card_submit.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreditCardPaymentActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        credit_card_submit.setVisibility(View.VISIBLE);
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

        RequestQueue requestQueus = Volley.newRequestQueue(CreditCardPaymentActivity.this);
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
                                    Toast.makeText(CreditCardPaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    credit_card_submit.setVisibility(View.VISIBLE);
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

                                Toast.makeText(CreditCardPaymentActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CreditCardPaymentActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                credit_card_submit.setVisibility(View.VISIBLE);
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

        RequestQueue requestQueue = Volley.newRequestQueue(CreditCardPaymentActivity.this);
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
                                    Toast.makeText(CreditCardPaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    credit_card_submit.setVisibility(View.VISIBLE);
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

                                Toast.makeText(CreditCardPaymentActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CreditCardPaymentActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                credit_card_submit.setVisibility(View.VISIBLE);
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
        RequestQueue requestQueue = Volley.newRequestQueue(CreditCardPaymentActivity.this);
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
                            Toast.makeText(CreditCardPaymentActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("anyText1",e.toString());
                            loading.setVisibility(View.GONE);
                            credit_card_submit.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreditCardPaymentActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        credit_card_submit.setVisibility(View.VISIBLE);
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

        RequestQueue requestQueus = Volley.newRequestQueue(CreditCardPaymentActivity.this);
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
                                    Toast.makeText(CreditCardPaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    credit_card_submit.setVisibility(View.VISIBLE);
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
                                Toast.makeText(CreditCardPaymentActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(CreditCardPaymentActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                credit_card_submit.setVisibility(View.VISIBLE);
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

        RequestQueue requestQueue = Volley.newRequestQueue(CreditCardPaymentActivity.this);
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
                                credit_card_submit.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading_screen.setVisibility(View.GONE);
                                startActivity(new Intent(CreditCardPaymentActivity.this, OrderConfirmationActivity.class));
                                finish();
                            }else{
                                total_items_check  = total_items_check + 1;
                            }

                            //Toast.makeText(MpesaPaymentActivity.this, "total_items_check" + total_items_check, Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(CreditCardPaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            credit_card_submit.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading_screen.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreditCardPaymentActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                        Toast.makeText(CreditCardPaymentActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        credit_card_submit.setVisibility(View.VISIBLE);
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

        RequestQueue requestQueue = Volley.newRequestQueue(CreditCardPaymentActivity.this);
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CreditCardPaymentActivity.this,CheckOutActivity.class));
        finish();
    }
}
