package com.example.techmasala;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.techmasala.APIcalls.TechApi;
import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.Adapters.RecyclerView_Adapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class News_web_view extends Fragment {
    private Context context;
    private Activity activity;
    private String url;
    private WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_web_view, container, false);
        this.url = getArguments().getString("url");
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        this.context = getContext();
        this.activity = getActivity();
         webView= activity.findViewById(R.id.web_host);
        ShimmerFrameLayout shimmer=activity.findViewById(R.id.news_host_shimmer);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {

                super.onPageStarted(view, url, favicon);
                webView.setVisibility(View.VISIBLE);
//                shimmer.startShimmer();

            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }

            @Override
            public void onPageCommitVisible (WebView view, String url){
                shimmer.stopShimmer();
                shimmer.setVisibility(View.GONE);
            }
            @Override public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                webView.stopLoading();
//                showDialog("Pls Check your Internet Connection and try again :/ ",context,"Retry","No");
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO hide your progress image
                super.onPageFinished(view, url);


                webView.setVisibility(View.VISIBLE);
//                Toast.makeText(context,"loaded",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void showDialog(String msg, Context context, String buttonNameFirst, String buttonNameSecond){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                buttonNameFirst,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        webView.loadUrl(url);
                    }
                });

        builder1.setNegativeButton(
                buttonNameSecond,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}