package com.alarayf.alarayf;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class Event_datil extends AppCompatActivity {

    Customer customer;

    String Eventid;
    String EventTitle;

    private FirebaseAnalytics mFirebaseAnalytics;


    // Becuase we want to share this with Event detail activity
    public static final String Event_id_P = "Event_id_P";
    public static final String Event_title = "Event_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Event_Detail_Screen", null);
        super.onCreate(savedInstanceState);
        setContentView(com.alarayf.alarayf.R.layout.activity_event_datil);
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("المناسبات");

        customer = (Customer) getApplicationContext();


        // set the background image
        LinearLayout RL = (LinearLayout)findViewById(com.alarayf.alarayf.R.id.content_event_datil);

        RL.setBackgroundResource(customer.Page_Background );
//######################### Recived data from Event #########################
        // get the passed var from EventList activity
        Eventid = getIntent().getStringExtra(EventList.Event_id_P);
        EventTitle = getIntent().getStringExtra(EventList.Event_Title);
        String EventDesc = getIntent().getStringExtra(EventList.Event_Desc);
        String EventDate = getIntent().getStringExtra(EventList.Event_Date);

        TextView title = (TextView) findViewById(com.alarayf.alarayf.R.id.title);
        title.setText(EventTitle);

        TextView desc = (TextView) findViewById(com.alarayf.alarayf.R.id.desc);
        desc.setText(EventDesc);

        TextView date = (TextView) findViewById(com.alarayf.alarayf.R.id.date);
        date.setText(EventDate);
//###########################Change font##############################################

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"Laha.ttf");

        title.setTypeface(mytypeface);
        //desc.setTypeface(mytypeface);
        //date.setTypeface(mytypeface);

//#########################################################################

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void comments_BtnClicked(View view) {

        Intent intent =  new Intent(Event_datil.this,Comments.class);

        intent.putExtra("News_id_P",Eventid);

        intent.putExtra("News_title",EventTitle);

        intent.putExtra("type",0); // 0 mean for news comments

        startActivity(intent);


    }
}
