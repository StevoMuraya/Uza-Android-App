package com.example.uza.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.uza.ActivityUzaHomePage;
import com.example.uza.CustomSettingsAdapter;
import com.example.uza.MainActivity;
import com.example.uza.MyOrdersActivity;
import com.example.uza.R;
import com.example.uza.UserLoginActivity;
import com.example.uza.UserRegistrationActivity;
import com.example.uza.clsGlobal;

public class ProfileFragment extends Fragment {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String shared_user_id,shared_fname,shared_lname,shared_email,
            shared_phone,shared_password,shared_location,shared_date_reg,shared_prof_pic;

    private String[] actions = {"My Orders","Logout"};
    private Integer[] images = {R.drawable.ic_shopping_cart_black_24dp,R.drawable.ic_exit_to_app_black_24dp};

    private TextView name,email;
    private ListView settings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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

        if (!(shared_user_id.equals(""))){
            View root = inflater.inflate(R.layout.fragment_profile, container, false);
            return root;
        }else{
            Toast.makeText(getActivity(), "You are required to log in or register to access your profile", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), UserLoginActivity.class));
            getActivity().finish();
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.profile_user_name);
        email = view.findViewById(R.id.profile_user_email);
        settings = view.findViewById(R.id.profile_settings);

        name.setText(shared_fname + " " + shared_lname);
        email.setText(shared_email);

        CustomSettingsAdapter Adapter = new CustomSettingsAdapter(getActivity(), actions,images);
        settings.setAdapter(Adapter);
        setListViewHeightBasedOnChildren(settings,1);
        settings.setFocusable(false);

        settings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String setting = ((TextView) view.findViewById(R.id.tv_settings_text)).getText().toString();
                if(setting.equalsIgnoreCase("My Orders")){
                    startActivity(new Intent(getActivity(), MyOrdersActivity.class));
                    getActivity().finish();
                }else if (setting.equalsIgnoreCase("Logout")){
                    Prompt_Logout(getActivity()).show();
                }
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView, int columns){
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null){
            //pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows  = 0;

        View listitems = listAdapter.getView(0, null, listView);
        listitems.measure(0, 0);
        totalHeight = listitems.getMeasuredHeight();

        float x = 1;

        if(items > columns){
            x = items/columns;
            rows = (int)(x);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    public AlertDialog.Builder Prompt_Logout(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage("Are you sure you want to logout of Uza?\n\n" +
                "You will be signed out of the app but all your transactions will be saved in our database");

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor = pref.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        return builder;
    }
}
