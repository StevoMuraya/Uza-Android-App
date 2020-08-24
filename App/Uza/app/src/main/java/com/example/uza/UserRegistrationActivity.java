package com.example.uza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText fname,lname,email,phone,county,pass,confirm;
    private Button register;
    private ProgressBar loading;
    private RelativeLayout loading_screen;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
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

        fname = findViewById(R.id.et_fname_reg);
        lname = findViewById(R.id.et_lname_reg);
        email = findViewById(R.id.et_email_reg);
        phone = findViewById(R.id.et_phone_reg);
        county = findViewById(R.id.et_county_reg);
        pass = findViewById(R.id.et_pass_reg);
        confirm = findViewById(R.id.et_pass_confirm_reg);

        loading = findViewById(R.id.reg_loading);
        loading_screen = findViewById(R.id.reg_loading_screen);

        register = findViewById(R.id.bt_reg_reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt_register();
            }
        });
    }


    public void attempt_register(){
        String fname_z      = fname.getText().toString();
        String lname_z       = lname.getText().toString();
        String email_z       = email.getText().toString();
        String phone_z       = phone.getText().toString();
        String county_z       = county.getText().toString();
        String pass_z           = pass.getText().toString();
        String confirm_z       = confirm.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(fname_z)) {
            fname.setError("First name is required");
            focusView = fname;
            cancel = true;
        }else if (TextUtils.isEmpty(lname_z)) {
            lname.setError("Last name is required");
            focusView = lname;
            cancel = true;
        }else if (TextUtils.isEmpty(email_z)) {
            email.setError("Type email to proceed");
            focusView = email;
            cancel = true;
        }else if (TextUtils.isEmpty(phone_z)) {
            phone.setError("Type phone to proceed");
            focusView = phone;
            cancel = true;
        }else if (!isPhoneValid(phone_z)) {
            phone.setError("Phone number is too short");
            focusView = phone;
            cancel = true;
        }else if (!isPhoneValid2(phone_z)) {
            phone.setError("Phone number is invalid");
            focusView = phone;
            cancel = true;
        }else if (TextUtils.isEmpty(county_z)) {
            county.setError("Type county you're located");
            focusView = county;
            cancel = true;
        }else if (TextUtils.isEmpty(pass_z)) {
            pass.setError("Password is required");
            focusView = pass;
            cancel = true;
        }else if (!isPassValid(pass_z)) {
            pass.setError("password is too short");
            focusView = pass;
            cancel = true;
        }else if (TextUtils.isEmpty(confirm_z)) {
            confirm.setError("Please confirm password");
            focusView = confirm;
            cancel = true;
        }else if (!confirmPass(confirm_z)) {
            confirm.setError("Passwords don't match!");
            focusView = confirm;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }else{
            register.setVisibility(View.GONE);
            loading_screen.animate().alpha(0.5f).setDuration(500);
            loading.setVisibility(View.VISIBLE);
            fname.setEnabled(false);
            lname.setEnabled(false);
            email.setEnabled(false);
            phone.setEnabled(false);
            county.setEnabled(false);
            pass.setEnabled(false);
            confirm.setEnabled(false);
            check_email_phone(email_z,phone_z,county_z,fname_z,lname_z,pass_z);
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

    private void registerUser(final String fname_x,final String lname_x,final String email_x, final String phone_x,final String county_x, final String password_x){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.REGISTER_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (response.equalsIgnoreCase("Successfully Registered")){
                            user_login(email_x,password_x);
                        }else{
                            new CountDownTimer(2500, 4000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    register.setVisibility(View.VISIBLE);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    fname.setEnabled(true);
                                    lname.setEnabled(true);
                                    email.setEnabled(true);
                                    phone.setEnabled(true);
                                    county.setEnabled(true);
                                    pass.setEnabled(true);
                                    confirm.setEnabled(true);
                                    Toast.makeText(UserRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
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
                                register.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                fname.setEnabled(true);
                                lname.setEnabled(true);
                                email.setEnabled(true);
                                phone.setEnabled(true);
                                county.setEnabled(true);
                                pass.setEnabled(true);
                                confirm.setEnabled(true);
                                Toast.makeText(UserRegistrationActivity.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserRegistrationActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fname",fname_x);
                params.put("lname",lname_x);
                params.put("email",email_x);
                params.put("phone",phone_x);
                params.put("county",county_x);
                params.put("password",password_x);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UserRegistrationActivity.this);
        requestQueue.add(stringRequest);
    }

    private void check_email_phone(final String email_z, final String phone_z, final String county_z, final String fname_z, final String lname_z, final String pass_z){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clsGlobal.CHECK_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respone_new",response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.optString("success");
                    String message = jsonObject.optString("message");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equalsIgnoreCase("1")){
                        new CountDownTimer(2500, 4000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                register.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                fname.setEnabled(true);
                                lname.setEnabled(true);
                                email.setEnabled(true);
                                phone.setEnabled(true);
                                county.setEnabled(true);
                                pass.setEnabled(true);
                                confirm.setEnabled(true);
                                dublicateFound(UserRegistrationActivity.this).show();
                            }

                        }.start();
                    }else{
                        registerUser(fname_z,lname_z,email_z,phone_z,county_z,pass_z);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    new CountDownTimer(2500, 4000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                        }
                        @Override
                        public void onFinish() {
                            register.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            fname.setEnabled(true);
                            lname.setEnabled(true);
                            email.setEnabled(true);
                            phone.setEnabled(true);
                            pass.setEnabled(true);
                            confirm.setEnabled(true);
                            Toast.makeText(UserRegistrationActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                            Toast.makeText(UserRegistrationActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                                register.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                fname.setEnabled(true);
                                lname.setEnabled(true);
                                email.setEnabled(true);
                                phone.setEnabled(true);
                                pass.setEnabled(true);
                                confirm.setEnabled(true);
                                Toast.makeText(UserRegistrationActivity.this, "Failed to check details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserRegistrationActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }

                        }.start();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email_z);
                params.put("phone",phone_z);
                return params;
            }
        };

        RequestQueue requestQueus = Volley.newRequestQueue(this);
        requestQueus.add(stringRequest);
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

                            String user_id_x = object.getString("user_id").trim();
                            String fname_x = object.getString("fname").trim();
                            String lname_x = object.getString("lname").trim();
                            String email_x = object.getString("email").trim();
                            String phone_x = object.getString("phone").trim();
                            String location_x = object.getString("county").trim();
                            String date_reg_x = object.getString("date_time_reg").trim();
                            String profile_pic_x = object.getString("profile_pic").trim();

                            editor.putString("shared_user_id",user_id_x);
                            editor.putString("shared_fname",fname_x);
                            editor.putString("shared_lname",lname_x);
                            editor.putString("shared_email", email_x);
                            editor.putString("shared_phone", phone_x);
                            editor.putString("shared_location", location_x);
                            editor.putString("shared_date_reg", date_reg_x);
                            editor.putString("shared_prof_pic", profile_pic_x);
                            editor.putString("shared_driver_password", pass_z);
                            editor.apply();

                            new CountDownTimer(2500, 4000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    register.setVisibility(View.VISIBLE);
                                    loading_screen.animate().alpha(0f).setDuration(500);
                                    loading.setVisibility(View.GONE);
                                    fname.setEnabled(true);
                                    lname.setEnabled(true);
                                    email.setEnabled(true);
                                    phone.setEnabled(true);
                                    county.setEnabled(true);
                                    pass.setEnabled(true);
                                    confirm.setEnabled(true);
                                    Toast.makeText(UserRegistrationActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserRegistrationActivity.this,ActivityUzaHomePage.class));
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
                                register.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                fname.setEnabled(true);
                                lname.setEnabled(true);
                                email.setEnabled(true);
                                phone.setEnabled(true);
                                county.setEnabled(true);
                                pass.setEnabled(true);
                                confirm.setEnabled(true);
                                Toast.makeText(UserRegistrationActivity.this, "Incorrect details used", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserRegistrationActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                            register.setVisibility(View.VISIBLE);
                            loading_screen.animate().alpha(0f).setDuration(500);
                            loading.setVisibility(View.GONE);
                            fname.setEnabled(true);
                            lname.setEnabled(true);
                            email.setEnabled(true);
                            phone.setEnabled(true);
                            pass.setEnabled(true);
                            confirm.setEnabled(true);
                            Toast.makeText(UserRegistrationActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            Toast.makeText(UserRegistrationActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            finish();
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
                                register.setVisibility(View.VISIBLE);
                                loading_screen.animate().alpha(0f).setDuration(500);
                                loading.setVisibility(View.GONE);
                                fname.setEnabled(true);
                                lname.setEnabled(true);
                                email.setEnabled(true);
                                phone.setEnabled(true);
                                county.setEnabled(true);
                                pass.setEnabled(true);
                                confirm.setEnabled(true);
                                Toast.makeText(UserRegistrationActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserRegistrationActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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


    public AlertDialog.Builder dublicateFound(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("The email or phone number you used to register already exists in the system,\n" +
                "Please try again using a different email or phone number");
        builder.setTitle("Dublicate Found");

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                email.setText("");
                phone.setText("");
                pass.setText("");
                confirm.setText("");
            }
        });

        builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        return builder;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserRegistrationActivity.this,UserLoginActivity.class));
        finish();
    }
}
