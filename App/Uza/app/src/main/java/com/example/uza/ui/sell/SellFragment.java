package com.example.uza.ui.sell;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.uza.ProductViewActivity;
import com.example.uza.R;
import com.example.uza.SellingActivity;
import com.example.uza.UserLoginActivity;
import com.example.uza.clsGlobal;

public class SellFragment extends Fragment {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;
    private Button start;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sell, container, false);
        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        start = view.findViewById(R.id.bt_sell_sell);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(shared_user_id.equals(""))){

                }else{
                    Prompt_Login(getActivity()).show();
                }
            }
        });
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
                startActivity(new Intent(getActivity(), UserLoginActivity.class));
                getActivity().finish();
            }
        });

        return builder;
    }
}
