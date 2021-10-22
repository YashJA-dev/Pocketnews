package com.example.techmasala;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Toolbar toolbar=findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        drawerlayout=findViewById(R.id.drawel_layout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerlayout,toolbar,
                R.string.nav_open,R.string.nav_close);
        NavigationView navView=findViewById(R.id.navigation_View);
        form_Transection("top-headlines?country=in&apiKey=e613af58839749aab8f66bba967ab5a8");
//        form_Transection2("www.youtube.com");
        navView.setCheckedItem(R.id.top_10_news);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem clickedItem) {
                MenuItem prevClicked=navView.getCheckedItem();
                if(prevClicked!=null&&selectedItem(prevClicked,clickedItem))return true;
                switch (clickedItem.getItemId()){
                    case R.id.techNav:
                        form_Transection("top-headlines?sources=techcrunch&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.sportNav:
                        form_Transection("top-headlines?country=in&category=sports&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.top_10_news:
                        form_Transection("top-headlines?country=in&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.market_news:
                        form_Transection("top-headlines?country=in&category=business&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.bollywood_news:
                        form_Transection("top-headlines?country=in&category=entertainment&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.science:
                        form_Transection("top-headlines?country=in&category=science&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.bitcoin:
                        form_Transection("everything?q=bitcoin&apiKey=e613af58839749aab8f66bba967ab5a8");
                        break;
                    case R.id.health_news:
                        form_Transection("top-headlines?country=in&category=health&apiKey=e613af58839749aab8f66bba967ab5a8");
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
        newsMain.putString("urlSecond",url);
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