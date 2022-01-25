package com.alarayf.alarayf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Vote extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(com.alarayf.alarayf.R.layout.activity_vote);
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        customer = (Customer)getApplicationContext();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Vote_Screen", null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView myWebView = (WebView) findViewById(com.alarayf.alarayf.R.id.mywebview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);

        myWebView.getSettings().setBuiltInZoomControls(true);

        //https://youtu.be/MMTnYNxHLc4
        //myWebView.loadUrl("https://www.google.com");
        myWebView.loadUrl(customer.Vote_URL);

    }

}
