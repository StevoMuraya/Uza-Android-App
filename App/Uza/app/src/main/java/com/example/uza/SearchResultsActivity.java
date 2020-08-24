package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultsActivity extends AppCompatActivity {
    private TextView result;
    private RecyclerView search_list;
    List<SearchResults> listSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
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

        Bundle bundle = getIntent().getExtras();
        String content = bundle.getString("content");

        result  =findViewById(R.id.result_search);
        result.setText(content);

        search_list = findViewById(R.id.search_recycler_view);
        search_list.setHasFixedSize(true);
        LinearLayoutManager verticalScroll = new LinearLayoutManager(SearchResultsActivity.this,LinearLayoutManager.VERTICAL,false);
        search_list.setLayoutManager(verticalScroll);
        search_list.setNestedScrollingEnabled(false);
        search_list.setFocusable(false);
        listSearch= new ArrayList<>();

        load_results(content);
    }

    private void load_results(final String content){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.SEARCH_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText1",response);
                        try {
                            JSONArray search = new JSONArray(response);
                            int count = 0;
                            for(int i=0;i<search.length();i++){
                                JSONObject gamesObject = search.getJSONObject(i);

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

                                SearchResults searchResults = new SearchResults(product_id,product_name,category,description,quantity,original_price,price,
                                        seller_name,rating,percentage_strike,product_image,date_added);
                                listSearch.add(searchResults);

                                SearchAdapter adapter = new SearchAdapter(SearchResultsActivity.this, listSearch);
                                search_list.setAdapter(adapter);
                                count++;
                            }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SearchResultsActivity.this, "Error 1", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchResultsActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(SearchResultsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("search",content);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(this);
        requestQueus.add(stringRequest);
    }
}
