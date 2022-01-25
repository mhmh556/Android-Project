package com.alarayf.alarayf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alarayf.alarayf.ServiceAPI.MobileVersionAPI;
import com.alarayf.alarayf.ServiceAPI.VoteBtnAPI;
import com.alarayf.alarayf.models.MobileVersion_data;
import com.alarayf.alarayf.models.Vote_btn_data;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



// were extend from AppCompatActivity
public class MainActivity extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;



    String App_Version = BuildConfig.VERSION_NAME;

    private List<MobileVersion_data> data_response;

    private List<Vote_btn_data> vote_data_response;

    private FloatingActionButton fab;

///######################## onCreate ######################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       customer = (Customer)getApplicationContext();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


       // NotificationManagerCompat.from(context).areNotificationsEnabled();

//************

        setContentView(com.alarayf.alarayf.R.layout.activity_main);


        // set the background image
        LinearLayout RL = (LinearLayout)findViewById(com.alarayf.alarayf.R.id.content_main);
        RL.setBackgroundResource(customer.Main_Background);


//************
        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("الرئيسية");




//************

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();


            }
        });*/

        fab = (FloatingActionButton) findViewById(com.alarayf.alarayf.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });
//************


        // vote button
        getVoteFromAPI();

        Button Btn1 = (Button) findViewById(com.alarayf.alarayf.R.id.btn1);
        Button Btn2 = (Button) findViewById(com.alarayf.alarayf.R.id.btn2);
        Button Btn3 = (Button) findViewById(com.alarayf.alarayf.R.id.btn3);
        Button Btn4 = (Button) findViewById(com.alarayf.alarayf.R.id.btn4);
        Button Btn5 = (Button) findViewById(com.alarayf.alarayf.R.id.btn5);
        Button Btn6 = (Button) findViewById(com.alarayf.alarayf.R.id.btn6);
        Button Btn7 = (Button) findViewById(com.alarayf.alarayf.R.id.btn7);
        Button Btn8 = (Button) findViewById(com.alarayf.alarayf.R.id.btn8);

        Button Btn_s1 = (Button) findViewById(com.alarayf.alarayf.R.id.instagramBtn);
        Button Btn_s2 = (Button) findViewById(com.alarayf.alarayf.R.id.twitterBtn);


        Btn_s2.setBackground(getResources().getDrawable(com.alarayf.alarayf.R.drawable.instagram));
        Btn_s1.setBackground(getResources().getDrawable(com.alarayf.alarayf.R.drawable.tree0image));

        //TextView mytextView = (TextView) findViewById(R.id.Event_name);

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),customer.getFont_Name());



        if (customer.Cust_number==1){



            Btn1.setText("إضافة مناسبة");
            Btn2.setText("المناسبات");
            Btn3.setText("أخبار");
            Btn4.setText("شجرة الشوايبة");
            Btn5.setText("صور تاريخية");
            Btn6.setText("الفيديو");
            Btn7.setText("تواصل معنا");
            Btn8.setText("إستشارات");

        }
        else if(customer.Cust_number==2){

            //mytypeface = Typeface.createFromAsset(getAssets(),"Times New Roman Bold.ttf");


            Btn1.setText("إضافة مناسبة");
            Btn2.setText("المناسبات");
            Btn3.setText("أخبار");
            Btn4.setText("شجرة العرايف");
            Btn5.setText("جوال العرايف");
            Btn6.setText("الفيديو");
            Btn7.setText("تواصل معنا");
            Btn8.setText("إضافة إقتراح");
        }


        Btn1.setTypeface(mytypeface);
        Btn2.setTypeface(mytypeface);
        Btn3.setTypeface(mytypeface);
        Btn4.setTypeface(mytypeface);
        Btn5.setTypeface(mytypeface);
        Btn6.setTypeface(mytypeface);
        Btn7.setTypeface(mytypeface);
        Btn8.setTypeface(mytypeface);



        //Inflate the Button object using the data at the 'activity_main.xml' file
        Button instagramBtn = (Button) this.findViewById(com.alarayf.alarayf.R.id.instagramBtn);

        //Changing the background color of the Button using PorterDuff Mode - Multiply
        instagramBtn.getBackground().setColorFilter(getResources().getColor(com.alarayf.alarayf.R.color.buttonColor), PorterDuff.Mode.SRC_IN);

        //Set the color of the text displayed inside the button
        //bt_exButton.setTextColor(0xFF0000FF);

        //Render this Button again
        //bt_exButton.invalidate();

        Button twitterBtn = (Button) this.findViewById(com.alarayf.alarayf.R.id.twitterBtn);

        //Changing the background color of the Button using PorterDuff Mode - Multiply
        twitterBtn.getBackground().setColorFilter(getResources().getColor(com.alarayf.alarayf.R.color.buttonColor), PorterDuff.Mode.SRC_IN);

      //  FirebaseMessaging.getInstance().subscribeToTopic("test");
       String x = FirebaseInstanceId.getInstance().getToken();

        getMobileVersionFromAPI();


        // customize notifiation icon

        Check_Notification_Enabled();

    }

    private void Check_Notification_Enabled() {
        boolean xxx = NotificationsUtils.isNotificationEnabled(this) ;

        if (xxx == false){

            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("الاشعارات غير مفعلة")
                    .setMessage("أرجوا تفعيل الاشعارات لكي تتمكن من استقبال الاشعارات من التطبيق ")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    ///######################## Menu ######################################

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */
    ///#########################Menu Item Selected#####################################

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.alarayf.alarayf.R.id.action_settings) {
            //Intent intent =  new Intent(MainActivity.this,AboutUs.class);
            //
            // startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    ///######################## Btn_clicked ######################################

    public void Btn1_clicked(View view) {

        Intent intent =  new Intent(MainActivity.this,AddEvent.class);
        startActivity(intent);
    }



    public void Btn2_clicked(View view) {

       Intent intent =  new Intent(MainActivity.this,EventList.class);
        startActivity(intent);
    }

    public void Btn3_clicked(View view) {

        Intent intent =  new Intent(MainActivity.this,News.class);
        startActivity(intent);
    }

    public void Btn4_clicked(View view) {

        Intent intent =  new Intent(MainActivity.this,Tree.class);
        startActivity(intent);
    }

    public void Btn5_clicked(View view) {

        if(customer.Cust_number == 1) {
            Intent intent = new Intent(MainActivity.this, Pic.class);
            startActivity(intent);
        }
        else if (customer.Cust_number == 2)
        {
            TextView PageTitle;
            PageTitle = (TextView) findViewById(com.alarayf.alarayf.R.id.pageTilte);


            Intent intent = new Intent(MainActivity.this, AddMobil.class);
            startActivity(intent);

        }
    }

    public void Btn6_clicked(View view) {

        Intent intent =  new Intent(MainActivity.this,vid.class);
        startActivity(intent);
    }

    public void Btn7_clicked(View view) {



        if (customer.Cust_number==1) {

            Intent intent =  new Intent(MainActivity.this,ContactUs.class);
            startActivity(intent);
        }
        if (customer.Cust_number==2){

            Intent intent =  new Intent(MainActivity.this,ContactUs.class);

            intent.putExtra("type","1");
            startActivity(intent);
        }
    }

    public void Btn8_clicked(View view) {

        if (customer.Cust_number==1) {

            Intent intent = new Intent(MainActivity.this, Cons.class);
            startActivity(intent);
        }
        if (customer.Cust_number==2){

            Intent intent =  new Intent(MainActivity.this,ContactUs.class);

            intent.putExtra("type","0");
            startActivity(intent);
        }
    }



    public void instagramBtnClicked(View view) {

        Intent intent =  new Intent(MainActivity.this,Twitter.class);
        startActivity(intent);


    }
    public void twitterBtnClicked(View view) {

        Intent intent =  new Intent(MainActivity.this,Instagram.class);
        startActivity(intent);



    }

///############################ updateDisplay function ############################################

    protected void ShowMessage(String message) {

        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("تنبيه");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "موافق",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    //############################ getConsListFromAPI function ############################################

    private void getMobileVersionFromAPI(){
        // check if they is internet
        if(this.isOnline()) {
            get_Data();
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create instent from api
        MobileVersionAPI data1 = retrofit.create(MobileVersionAPI.class);

        Call<List<MobileVersion_data>> call = data1.getMobileVersionFromAPI();

        call.enqueue(new Callback<List<MobileVersion_data>>() {
            @Override
            public void onResponse(Call<List<MobileVersion_data>> call, Response<List<MobileVersion_data>> response) {
                // create var
                String d1="";

                // the data in data_response
                data_response = response.body();

                // if verstion not equal, show message
                if(!(data_response.get(0).getVersionNo().equals(App_Version)))
                {

                    Bundle bundle = new Bundle();
                    //bundle.putString("US", "image");
                    mFirebaseAnalytics.logEvent("App_Need_Update", null);
                    //Log.d("tttttt",data_response.get(0).getMessage());
                    ShowMessage(data_response.get(0).getMessage());

                }




       /*
        for(int i=0;i<data_response.size();i++){
        String name = data_response.get(i).getTitle();

        d1 += name;

        }
        Log.d("tttttt",d1);*/

            }

            @Override
            public void onFailure(Call<List<MobileVersion_data>> call, Throwable t) {
                Log.d("tttttt","فشل في الاتصال بالانترنت");

               // Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
            }
        });
    }


    //############################ getConsListFromAPI function ############################################

    private void getVoteFromAPI(){
        // check if they is internet
        if(this.isOnline()) {
            get_Data_vote();
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data_vote() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create instent from api
        VoteBtnAPI data1 = retrofit.create(VoteBtnAPI.class);

        Call<List<Vote_btn_data>> call = data1.getVoteFromAPI();

        call.enqueue(new Callback<List<Vote_btn_data>>() {
            @Override
            public void onResponse(Call<List<Vote_btn_data>> call, Response<List<Vote_btn_data>> response) {
                // create var
                String d1="";

                // the data in data_response
                vote_data_response = response.body();

                // if verstion not equal, show message
                if(!(vote_data_response.get(0).getVote_publish().equals("1")))
                {

                    Log.d("vote","vote publish is # " + vote_data_response.get(0).getVote_publish());

                    Button Btn_main = (Button) findViewById(R.id.btn_main);

                    Btn_main.setVisibility(View.GONE);

                }
                else
                {
                    Button Btn_main = (Button) findViewById(R.id.btn_main);


                    Btn_main.setText(vote_data_response.get(0).getVote_btn_title());
                    Btn_main.setVisibility(View.VISIBLE);

                    Log.d("vote","vote URl is # " + vote_data_response.get(0).getVote_url());
                    customer.setVote_URL(vote_data_response.get(0).getVote_url());

                    Log.d("vote","cuttomer vote URl is # " + customer.getVote_URL());

                }




       /*
        for(int i=0;i<data_response.size();i++){
        String name = data_response.get(i).getTitle();

        d1 += name;

        }
        Log.d("tttttt",d1);*/

            }

            @Override
            public void onFailure(Call<List<Vote_btn_data>> call, Throwable t) {
                Log.d("tttttt","فشل في الاتصال بالانترنت");

                // Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
            }
        });
    }


    //############################ connectivity  function ############################################
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public void Vote_btn_clicked(View view) {

        Intent intent =  new Intent(MainActivity.this,Vote.class);
        //Intent intent =  new Intent(MainActivity.this,Instagram.class);
        startActivity(intent);

    }
}
