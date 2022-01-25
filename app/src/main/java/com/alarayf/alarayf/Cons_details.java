package com.alarayf.alarayf;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.alarayf.alarayf.ServiceAPI.UpdateVisitAPI;
import com.alarayf.alarayf.models.Update_visit_data;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.alarayf.alarayf.ServiceAPI.UpdateVisitAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cons_details extends AppCompatActivity {

    Customer customer;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;

    Update_visit_data update_visit_data = new Update_visit_data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        customer = (Customer) getApplicationContext();

        setContentView(com.alarayf.alarayf.R.layout.activity_cons_details);
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Cons_Detail_Screen", null);

        getSupportActionBar().setTitle("الأخبار");

        //######################### Recived data from Event #########################
        // get the passed var from EventList activity
        String Consid = getIntent().getStringExtra(Cons.Cons_id);
        String ConsTitle = getIntent().getStringExtra(Cons.Cons_Title);
        String ConsDesc = getIntent().getStringExtra(Cons.Cons_Desc);
        String ConsImageURL = getIntent().getStringExtra(Cons.Cons_Image);


        TextView title = (TextView) findViewById(com.alarayf.alarayf.R.id.title);
        title.setText(ConsTitle);

        TextView desc = (TextView) findViewById(com.alarayf.alarayf.R.id.desc);
        desc.setText(ConsDesc);

        // load Cons image
        ImageView Cons_image = (ImageView) findViewById(com.alarayf.alarayf.R.id.Cons_image_d);

        // i have used the Picasso library
        Picasso.get().load(ConsImageURL).into(Cons_image);

//###########################Change font##############################################

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"Laha.ttf");

        title.setTypeface(mytypeface);
        //desc.setTypeface(mytypeface);

//#########################################################################


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //###################### Update visit count #############
        update_visit_data.setK2_item_id(Consid);
       insertVisitFromAPI(update_visit_data.getK2_item_id());
    }
    //############################ show and hide Progress function ############################################
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    //############################ set data function ############################################
    private void get_Data(String id) {
        //showpDialog();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        UpdateVisitAPI data1 = retrofit.create(UpdateVisitAPI.class);




        Call<List<Update_visit_data>> call = data1.increase_k2_item_visitFromAPI( id);



        call.enqueue(new Callback<List<Update_visit_data>>() {
            @Override
            public void onResponse(Call<List<Update_visit_data>> call, Response<List<Update_visit_data>> response) {
                // create var
                String d1="";
                int stautasCode= response.code();
                // the data in data_response
                //  responseDataStringtype = response.body();

                //updateDisplay();




                //Log.d("tttttt ", String.valueOf(stautasCode));

                //Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<Update_visit_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );

              //  hidepDialog();
            }
        });

    }

    //############################ Subment data function ############################################

    private void insertVisitFromAPI(String id){
        // check if they is internet
        if(this.isOnline()) {
            get_Data( id);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ connectivity  function ############################################
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
