package com.example.uza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
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
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductViewActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private ImageView imageView;
    private TextView name,category,price,price_strike,percent,quantity,rating,desription;
    private Button add_cart;
    private RelativeLayout loading_screen;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
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

        Bundle bundle = getIntent().getExtras();
        final String product_id = bundle.getString("product_id");
        final String product_name = bundle.getString("product_name");
        final String product_category = bundle.getString("product_category");
        final String product_description = bundle.getString("product_description");
        final String product_quantity = bundle.getString("product_quantity");
        final String original_price = bundle.getString("product_original_price");
        final String product_price = bundle.getString("product_price");
        final String seller_name = bundle.getString("seller_name");
        final String product_rating = bundle.getString("product_rating");
        final String product_percent = bundle.getString("product_percent");
        final String product_image = bundle.getString("product_image");


        imageView = findViewById(R.id.product_view_image);
        name = findViewById(R.id.product_view_name);
        category = findViewById(R.id.product_view_category);
        price = findViewById(R.id.product_view_price);
        price_strike = findViewById(R.id.product_view_price_strike);
        percent = findViewById(R.id.product_view_percent);
        quantity = findViewById(R.id.product_view_quantity);
        rating = findViewById(R.id.product_view_rating);
        desription = findViewById(R.id.product_view_description);
        add_cart = findViewById(R.id.product_view_add_to_cart);
        loading = findViewById(R.id.product_loading);
        loading_screen = findViewById(R.id.product_loading_screen);

        name.setText(product_name);
        category.setText(product_category);
        price.setText("Kshs "+product_price);
        percent.setText(product_percent+"%");
        quantity.setText(product_quantity);
        rating.setText(product_rating);
        desription.setText(product_description);

        int reduction = (Integer.parseInt(product_price)*(Integer.parseInt(product_percent)+100))/100;
        String reducted = String.valueOf(reduction);
        price_strike.setText("Kshs " + reducted);

        Picasso.get()
                .load(product_image)
                .resize(180,180)
                .centerInside()
                .error(R.drawable.ic_shopping_cart_black_24dp)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
//                        loading2.setVisibility(View.GONE);
//                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(shared_user_id.equals(""))){
                    add_cart.setEnabled(false);
                    loading_screen.animate().alpha(0.6f).setDuration(500);
                    loading.setVisibility(View.VISIBLE);
                    check_cart(product_id,shared_user_id,product_name,product_category,product_description,original_price,product_price,seller_name,product_rating,product_percent,product_image);
                }else{
                    Prompt_Login(ProductViewActivity.this).show();
                }
            }
        });
    }



    private void check_cart(final String product_id,final String user_id,final String product_name, final String category,
                            final String description,final String original_price,final String price,
                            final String seller_name,final String rating,final String percentage_strike,final String product_image){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.CHECK_CART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respone_new_new",response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.optString("success");
                    String message = jsonObject.optString("message");
                    JSONArray jsonArray = jsonObject.getJSONArray("cart");

                    if (success.equalsIgnoreCase("1")){
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                add_cart.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);

                                Prompt_In_Cart(ProductViewActivity.this).show();
                            }

                        }.start();
                    }else{
                        check_quantity(product_id,user_id,product_name,category,description,original_price,price,seller_name,rating,percentage_strike,product_image);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    new CountDownTimer(2500, 4000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            add_cart.setEnabled(true);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            Toast.makeText(ProductViewActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                            Toast.makeText(ProductViewActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                                add_cart.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                Toast.makeText(ProductViewActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProductViewActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_id",product_id);
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(this);
        requestQueus.add(stringRequest);
    }
    private void check_quantity(final String product_id,final String user_id,final String product_name, final String category,
                                final String description,final String original_price,final String price,
                                final String seller_name,final String rating,final String percentage_strike,final String product_image){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.CHECK_QUANTITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respone_new_new_new",response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.optString("success");
                    String message = jsonObject.optString("message");
                    JSONArray jsonArray = jsonObject.getJSONArray("quantity");

                    if (success.equalsIgnoreCase("1")){
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                add_cart.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);

                                Prompt_In_Quantity(ProductViewActivity.this).show();
                            }

                        }.start();
                    }else{
                        add_To_Cart(product_id,user_id,product_name,category,description,original_price,price,seller_name,rating,percentage_strike,product_image);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    new CountDownTimer(2500, 4000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            add_cart.setEnabled(true);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            Toast.makeText(ProductViewActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                            Toast.makeText(ProductViewActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                                add_cart.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                Toast.makeText(ProductViewActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProductViewActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("product_id",product_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(this);
        requestQueus.add(stringRequest);
    }
    private void add_To_Cart(final String product_id,final String user_id,final String product_name, final String category,
                             final String description,final String original_price,final String price,
                             final String seller_name,final String rating,final String percentage_strike,final String product_image){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.ADD_TO_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Added")){
                            //user_login(email_x,password_x);
                            add_cart.setEnabled(true);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            Toast.makeText(ProductViewActivity.this, "Successfully Added to Cart", Toast.LENGTH_SHORT).show();
                        }else{
                            new CountDownTimer(2500, 4000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    add_cart.setEnabled(true);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    Toast.makeText(ProductViewActivity.this, response, Toast.LENGTH_SHORT).show();
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
                                add_cart.setEnabled(true);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                Toast.makeText(ProductViewActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProductViewActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id",user_id);
                params.put("product_id",product_id);
                params.put("product_name",product_name);
                params.put("category",category);
                params.put("description",description);
                params.put("original_price",original_price);
                params.put("price",price);
                params.put("seller_name",seller_name);
                params.put("rating",rating);
                params.put("percentage_strike",percentage_strike);
                params.put("product_image",product_image);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ProductViewActivity.this);
        requestQueue.add(stringRequest);
    }



    public AlertDialog.Builder Prompt_Login(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("To make a purchase or order a product, you are required to Login or Sign up first.");

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ProductViewActivity.this, UserLoginActivity.class));
                finish();
            }
        });

        return builder;
    }

    public AlertDialog.Builder Prompt_In_Cart(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("This product is already in your cart, Proceed to your cart screen to checkout or remove from your cart list");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });

//        builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(ProductViewActivity.this, UserLoginActivity.class));
//                finish();
//            }
//        });

        return builder;
    }

    public AlertDialog.Builder Prompt_In_Quantity(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("This product is currently out of stock, Please check again later to make a purchase of the product");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });

//        builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(ProductViewActivity.this, UserLoginActivity.class));
//                finish();
//            }
//        });

        return builder;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProductViewActivity.this,ActivityUzaHomePage.class));
        finish();
    }
}
