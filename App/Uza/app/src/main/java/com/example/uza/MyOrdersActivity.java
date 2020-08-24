package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrdersActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private RecyclerView my_orders_recycler;
    private List<Orders> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
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

        my_orders_recycler = findViewById(R.id.my_orders_recycler);
        my_orders_recycler.setHasFixedSize(true);
        LinearLayoutManager horizontalScroll = new LinearLayoutManager(MyOrdersActivity.this,LinearLayoutManager.VERTICAL,false);
        my_orders_recycler.setLayoutManager(horizontalScroll);
        my_orders_recycler.setNestedScrollingEnabled(false);
        my_orders_recycler.setFocusable(false);
        ordersList= new ArrayList<>();

        load_orders(shared_user_id);
    }



    private void load_orders(final String user_id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.MY_ORDERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText1",response);
                        try {
                            JSONArray cart = new JSONArray(response);

                            for(int i=0;i<cart.length();i++){
                                JSONObject gamesObject = cart.getJSONObject(i);

                                String order_code = gamesObject.getString("order_code");
                                String items = gamesObject.getString("items");
                                String d_date = gamesObject.getString("d_date");

                                Orders orders = new Orders(order_code,items,d_date);
                                ordersList.add(orders);

                                OrdersAdapter adapter = new OrdersAdapter(MyOrdersActivity.this, ordersList);
                                my_orders_recycler.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MyOrdersActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyOrdersActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(MyOrdersActivity.this);
        requestQueus.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyOrdersActivity.this,ActivityUzaHomePage.class));
        finish();
    }
}
