package com.alarayf.alarayf;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AboutUs extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    String Version = BuildConfig.VERSION_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer)getApplicationContext();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("About_Us_Screen", null);

        setContentView(com.alarayf.alarayf.R.layout.activity_about_us);

        // set the background image
        ConstraintLayout RL = (ConstraintLayout)findViewById(com.alarayf.alarayf.R.id.content_about_us);
        RL.setBackgroundResource(customer.About_Us_Background);


        Button PoweredBy = (Button)findViewById(R.id.powered);

        PoweredBy.setText("Alnafizah Inc, " + Version);
        PoweredBy.setBackgroundColor(Color.argb(0,0,0,0));
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("حول البرنامج");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
  /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.) {
            // your code
            Log.d("SSSS"," back button was clecked");
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
          case android.R.id.home:
              finish();
              break;
      }
      return true;
  }

    public void powered_onclick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alnafizah.com"));
        startActivity(browserIntent);
    }
}
