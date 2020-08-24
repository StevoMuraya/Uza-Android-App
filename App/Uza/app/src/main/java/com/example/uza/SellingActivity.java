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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SellingActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private RecyclerView Categories;
    List<Categories> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        pref = SellingActivity.this.getSharedPreferences("uza.conf", Context.MODE_PRIVATE);
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
            setContentView(R.layout.activity_selling);

            Categories = findViewById(R.id.sell_category_select);

            Categories.setHasFixedSize(true);
            LinearLayoutManager horizontalScroll = new LinearLayoutManager(SellingActivity.this,LinearLayoutManager.VERTICAL,false);
            Categories.setLayoutManager(horizontalScroll);
            Categories.setNestedScrollingEnabled(false);
            Categories.setFocusable(false);
            categoryList= new ArrayList<>();

            load_categories();
        }else{
            Toast.makeText(SellingActivity.this, "You are required to log in or register to Sell Products", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SellingActivity.this, UserLoginActivity.class));
            finish();
        }

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

                                SellCategoryAdapter adapter = new SellCategoryAdapter(SellingActivity.this, categoryList);
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
        Volley.newRequestQueue(SellingActivity.this).add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(SellingActivity.this,ActivityUzaHomePage.class));
        finish();
    }
}
