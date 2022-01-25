package com.alarayf.alarayf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

public class TreeImage extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alarayf.alarayf.R.layout.activity_instagram);
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        customer = (Customer)getApplicationContext();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Tree_Screen", null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView myWebView = (WebView) findViewById(com.alarayf.alarayf.R.id.mywebview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        myWebView.setWebViewClient(new WebViewClient());

        WebSettings settings = myWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        //myWebView.loadUrl("https://www.instagram.com/alarayf/?hl=ar");
       // myWebView.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%; height:100%;\"><tr><td style=\"vertical-align:middle;\"><img src=\"" + customer.Tree_image_URL + "\"></td></tr></table></body></html>", "html/css", "utf-8", null);

        myWebView.loadUrl(customer.Tree_image_URL);

    }

}
