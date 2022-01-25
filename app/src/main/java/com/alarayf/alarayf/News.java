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


import com.alarayf.alarayf.Adapter.News_Adapter;

import com.alarayf.alarayf.ServiceAPI.NewsDataAPI;

import com.alarayf.alarayf.models.News_list_data;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import com.alarayf.alarayf.ServiceAPI.NewsDataAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class News extends AppCompatActivity {

    // customer class, check on create method, this needed for all activity in access the gloable variable.
    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;

    private List<News_list_data> data_response;

   // private String  IMAGE_PATH = "http://www.alarayf.com/media/k2/items/cache/";
    private  String MyimageURL = "";
    // for the image naming
    private String  XS = "_XS.jpg";
    private String  S = "_S.jpg";
    private String  M = "_M.jpg";
    private String  XL = "_XL.jpg";
    private String  L = "_L.jpg";

    // Becuase we want to share this with Event detail activity
    public static final String News_id = "News_id";
    public static final String News_Title = "News_Title";
    public static final String News_Desc = "News_Desc";
    public static final String News_Image = "News_Image";





    ///######################## onCreate ######################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("News_Button", null);
        //*************** Layout ***********************

        setContentView(com.alarayf.alarayf.R.layout.activity_news);
        // set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(com.alarayf.alarayf.R.id.content_news);

        RL.setBackgroundResource(customer.Page_Background );

        //*************** Progress bar ***********************

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        //*************** Tool  bar ***********************

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("الأخبار");
        //*************** floating button ***********************



        //*************** back button ***********************

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        //*************** get data ***********************

        getNewsListFromAPI();


        //*************** prosses selected item ***********************
        // when item get selected navigate to detial view

        ListView mylist = (ListView) findViewById(com.alarayf.alarayf.R.id.mylist);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(News.this, News_details.class);

                News_list_data news_data = data_response.get(position);

                intent.putExtra(News_id,news_data.getId());
                intent.putExtra(News_Title,news_data.getTitle());
                intent.putExtra(News_Desc,stripHtml(news_data.getIntrotext()));

                // send the URL of the image to news detail screen
                MyimageURL = customer.Server_image_Path+news_data.getImageName()+M;
                intent.putExtra(News_Image,MyimageURL);

                startActivity(intent);
            }
        });

    }
    ///############################ updateDisplay function ############################################

    protected void updateDisplay() {
        News_Adapter adapter = new News_Adapter(this, com.alarayf.alarayf.R.layout.row_news, this.data_response);
        ListView lv = (ListView) findViewById(com.alarayf.alarayf.R.id.mylist);
        lv.setAdapter(adapter);



        //this.setListAdapter(adapter);
    }

  //############################ getNewsListFromAPI function ############################################

    private void getNewsListFromAPI(){
        // check if they is internet
        if(this.isOnline()) {
            get_Data();
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data() {
        showpDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL) //"http://www.alarayf.com/m20/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create instent from api
        NewsDataAPI data1 = retrofit.create(NewsDataAPI.class);

        Call<List<News_list_data>> call = data1.getNewsListFromAPI();

        call.enqueue(new Callback<List<News_list_data>>() {
            @Override
            public void onResponse(Call<List<News_list_data>> call, Response<List<News_list_data>> response) {
                // create var
                String d1="";

                // the data in data_response
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
            public void onFailure(Call<List<News_list_data>> call, Throwable t) {
                hidepDialog();
                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
            }
        });
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

    //############################ connectivity  function ############################################
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //############################ remove html tag function ############################################
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}