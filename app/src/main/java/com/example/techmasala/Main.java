package com.example.techmasala;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class Main extends AppCompatActivity {
    private DrawerLayout drawerlayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        drawerlayout=findViewById(R.id.drawel_layout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerlayout,toolbar,
                R.string.nav_open,R.string.nav_close);
        NavigationView navView=findViewById(R.id.navigation_View);
        form_Transection("main loaded");
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem clickedItem) {
                MenuItem prevClicked=navView.getCheckedItem();
                if(prevClicked!=null&&selectedItem(prevClicked,clickedItem))return true;
                switch (clickedItem.getItemId()){
                    case R.id.techNav:
                        form_Transection("button Clicked");
                        break;
                    case R.id.sportNav:
                        form_Transection("sportNav");
                        break;
                }
                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }
    private void form_Transection(String url){
        Bundle newsMain=new Bundle();
        newsMain.putString("main",url);
        News_main news_main=new News_main();
        news_main.setArguments(newsMain);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,news_main).commit();
    }
    private boolean selectedItem(MenuItem prevClicked,MenuItem clicked){
        return prevClicked.equals(clicked);
    }
    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

}