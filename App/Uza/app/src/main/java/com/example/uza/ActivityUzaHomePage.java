package com.example.uza;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ActivityUzaHomePage extends AppCompatActivity {
    int exit_command = 0;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uza_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        searchView = findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(ActivityUzaHomePage.this, SearchResultsActivity.class);
                intent.putExtra("content", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }else if (exit_command == 0){
            exit_command = 1;
            Toast.makeText(ActivityUzaHomePage.this, "press back again to exit app", Toast.LENGTH_SHORT).show();
            new CountDownTimer(3000, 4000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    exit_command = 0;
                }

            }.start();
        }else if (exit_command == 1){
            finish();
        }
    }
}
