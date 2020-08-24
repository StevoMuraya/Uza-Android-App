package com.example.uza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    private Button login,join;
    private EditText email,pass;
    private ProgressBar loading;
    private TextView info;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;
    private RelativeLayout loading_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        this.getSupportActionBar().hide();

        pref = getSharedPreferences("uza.conf", Context.MODE_PRIVATE);
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

        email = findViewById(R.id.et_email_login);
        pass = findViewById(R.id.et_pass_login);

        loading = findViewById(R.id.login_loading);
        info = findViewById(R.id.login_reg_info);
        loading_screen = findViewById(R.id.loading_screen);

        login = findViewById(R.id.bt_login_login);
        join = findViewById(R.id.bt_reg_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt_login();
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this,UserRegistrationActivity.class));
                finish();
            }
        });
    }

    public void attempt_login(){
        String email_z       = email.getText().toString();
        String pass_z       = pass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email_z)) {
            email.setError("Type email to proceed");
            focusView = email;
            cancel = true;
        }else if (TextUtils.isEmpty(pass_z)) {
            pass.setError("Password is required");
            focusView = pass;
            cancel = true;
        }else if (!isPassValid(pass_z)) {
            pass.setError("password is too short");
            focusView = pass;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }else{
            login.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
            join.setVisibility(View.GONE);
            loading_screen.animate().alpha(0.5f).setDuration(500);
            loading.setVisibility(View.VISIBLE);
            email.setEnabled(false);
            pass.setEnabled(false);
            user_login(email_z,pass_z);
            //Toast.makeText(this, "Goot to go", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isPhoneValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 9;
    }
    private boolean isPhoneValid2(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith("07");
    }
    private boolean isPassValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }
    private boolean confirmPass(String conf) {
        //TODO: Replace this with your own logic
        return conf.equals(pass.getText().toString());
    }


    private void user_login(final String email_z, final String pass_z){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.LOGIN_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respone_new",response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")){
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String user_id = object.getString("user_id").trim();
                            String fname = object.getString("fname").trim();
                            String lname = object.getString("lname").trim();
                            String email_z = object.getString("email").trim();
                            String phone = object.getString("phone").trim();
                            String location = object.getString("county").trim();
                            String date_reg = object.getString("date_time_reg").trim();
                            String profile_pic = object.getString("profile_pic").trim();

                            editor.putString("shared_user_id",user_id);
                            editor.putString("shared_fname",fname);
                            editor.putString("shared_lname",lname);
                            editor.putString("shared_email", email_z);
                            editor.putString("shared_phone", phone);
                            editor.putString("shared_location", location);
                            editor.putString("shared_date_reg", date_reg);
                            editor.putString("shared_prof_pic", profile_pic);
                            editor.putString("shared_driver_password", pass_z);
                            editor.apply();

                            new CountDownTimer(2500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    login.setVisibility(View.VISIBLE);
                                    info.setVisibility(View.VISIBLE);
                                    join.setVisibility(View.VISIBLE);
                                    email.setEnabled(true);
                                    pass.setEnabled(true);
                                    Toast.makeText(UserLoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserLoginActivity.this,ActivityUzaHomePage.class));
                                    finish();
                                }

                            }.start();

                        }
                    }else {
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);
                                info.setVisibility(View.VISIBLE);
                                join.setVisibility(View.VISIBLE);
                                email.setEnabled(true);
                                pass.setEnabled(true);
                                Toast.makeText(UserLoginActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserLoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    new CountDownTimer(2500, 4000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);
                            info.setVisibility(View.VISIBLE);
                            join.setVisibility(View.VISIBLE);
                            email.setEnabled(true);
                            pass.setEnabled(true);
                            Toast.makeText(UserLoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            Toast.makeText(UserLoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);
                                info.setVisibility(View.VISIBLE);
                                join.setVisibility(View.VISIBLE);
                                email.setEnabled(true);
                                pass.setEnabled(true);
                                Toast.makeText(UserLoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserLoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email_z);
                params.put("password",pass_z);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(this);
        requestQueus.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserLoginActivity.this,ActivityUzaHomePage.class));
        finish();
    }
}
