package com.alarayf.alarayf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alarayf.alarayf.Adapter.Event_Adapter;
import com.alarayf.alarayf.Adapter.Tree_last_added_Adapter;
import com.alarayf.alarayf.ServiceAPI.EventDataAPI;
import com.alarayf.alarayf.ServiceAPI.TreeLastAddedDataAPI;
import com.alarayf.alarayf.models.Event_list_data;
import com.alarayf.alarayf.models.Tree_last_added_list_data;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// was extended from AppCompatActivity
public class Tree_last_added_List extends AppCompatActivity {

    Customer customer;
    private FirebaseAnalytics mFirebaseAnalytics;

    private ProgressDialog pDialog;

    private List<Tree_last_added_list_data> data_response;



    ///######################## onCreate ######################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();

        setContentView(R.layout.activity_tree_last_added_list);



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Tree_last_Name_Screen", null);



        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);
        //***********

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("آخر الأسماء المضافين");
        //***********

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //***********

       // Typeface mytypeface = Typeface.createFromAsset(getAssets(),"MyFont1.ttf");
        //TextView mytextView = (TextView) findViewById(R.id.Event_name);
       // mytextView.setTypeface(mytypeface);

        getTree_last_name_ListFromAPI();


        // when item get selected navigate to detial view
            ListView mylist = (ListView) findViewById(R.id.mylist);

            /*
            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Tree_last_added_List.this, Event_datil.class);

                    Event_list_data event_data = data_response.get(position);

                    intent.putExtra(Event_id_P,event_data.getId());
                    intent.putExtra(Event_Title,event_data.getTitle());
                    intent.putExtra(Event_Desc,stripHtml(event_data.getDesc()));
                    intent.putExtra(Event_Date,"موعد المناسبة "+event_data.getStartdate()+event_data.getRemain());
                    startActivity(intent);
                }
            });*/



        // set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(R.id.content_event_list);

        RL.setBackgroundResource(customer.Page_Background );


        String x = customer.getFont_Name();

        //Typeface mytypeface1 = Typeface.createFromAsset(getAssets(),customer.getFont_Name());
        //Typeface mytypeface1 = Typeface.createFromAsset(getAssets(),"Times New Roman Bold.ttf");


       // TextView Event_name = (TextView) findViewById(R.id.Event_name99);
        //Event_name.setTextSize(4);
        //Event_name.setTypeface(mytypeface1);

    }

   ///########################################################################

    protected void updateDisplay() {
        Tree_last_added_Adapter adapter = new Tree_last_added_Adapter(this, R.layout.row_tree_last_added_list, this.data_response);
        ListView lv = (ListView) findViewById(R.id.mylist);
        lv.setAdapter(adapter);


        //this.setListAdapter(adapter);
    }
private void getTree_last_name_ListFromAPI(){
    if(this.isOnline()) {
    showpDialog();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(customer.Site_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //create instent from api
    TreeLastAddedDataAPI data1 = retrofit.create(TreeLastAddedDataAPI.class);

    Call<List<Tree_last_added_list_data>> call = data1.getTreeLastAddedListFromAPI();

    call.enqueue(new Callback<List<Tree_last_added_list_data>>() {
        @Override
        public void onResponse(Call<List<Tree_last_added_list_data>> call, Response<List<Tree_last_added_list_data>> response) {
            // create var
            String d1="";

            data_response = response.body();

            updateDisplay();

            hidepDialog();
           /*
            for(int i=0;i<data_response.size();i++){
            String name = data_response.get(i).getTitle();

            d1 += name;

            }
            Log.d("tttttt",d1);*/

        }

        @Override
        public void onFailure(Call<List<Tree_last_added_list_data>> call, Throwable t) {
            hidepDialog();
        }
    });
    } else {
        Toast.makeText(this, "Network isn\'t available", Toast.LENGTH_LONG).show();
    }

}


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}

