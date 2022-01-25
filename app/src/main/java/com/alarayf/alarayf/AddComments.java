package com.alarayf.alarayf;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alarayf.alarayf.ServiceAPI.AddCommentsAPI;
import com.alarayf.alarayf.ServiceAPI.AddMobileAPI;
import com.alarayf.alarayf.ServiceAPI.NotificationByEmailAPI;
import com.alarayf.alarayf.models.Add_EmailNotification_data;
import com.alarayf.alarayf.models.Add_comments_data;
import com.alarayf.alarayf.models.Add_mobile_data;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import com.alarayf.alarayf.ServiceAPI.NotificationByEmailAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddComments extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;
    private List<Add_comments_data> data_response;
    private String responseDataStringtype="";

    EditText NameField ;

    EditText messageField;

    Button SendButton;




    String item_id;
    String item_title;



    Add_comments_data add_comments_data = new Add_comments_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();

        setContentView(com.alarayf.alarayf.R.layout.activity_add_comments);

// set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(com.alarayf.alarayf.R.id.content_add_comments);

        RL.setBackgroundResource(customer.Page_Background );

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("add_comments_Inserted", null);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("التعليقات");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NameField = (EditText) findViewById(com.alarayf.alarayf.R.id.Name_us);


        messageField = (EditText) findViewById(com.alarayf.alarayf.R.id.message_us);



        SendButton = (Button) findViewById(com.alarayf.alarayf.R.id.send);

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"Laha.ttf");




        //######################### Recived data from Event #########################
        // get the passed var from EventList activity

        item_id = getIntent().getStringExtra(Comments.item_id);

        item_title =  getIntent().getStringExtra(Comments.item_title);


        TextView PageTitle = (TextView) findViewById(R.id.pageTilte);
        PageTitle.setText("إضافة تعليق على: " + item_title);
        NameField.requestFocus();

    }

    //############################ Subment data function ############################################

    private void insertCommentsFromAPI(String itmenId, String userId, String Name, String message, String Publish){
        // check if they is internet
        if(this.isOnline()) {
            get_Data( itmenId, userId, Name,   message,  Publish);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data(String itmenId, String userId, String Name, String message, String Publish) {
        //showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        AddCommentsAPI data1 = retrofit.create(AddCommentsAPI.class);




        Call<List<Add_comments_data>> call = data1.insertCommentsFromAPI( itmenId, userId,  Name,  message,  Publish);

        call.enqueue(new Callback<List<Add_comments_data>>() {
            @Override
            public void onResponse(Call<List<Add_comments_data>> call, Response<List<Add_comments_data>> response) {
                // create var
                String d1="";
                int stautasCode = response.code();
                // the data in data_response
                //  responseDataStringtype = response.body();

                //updateDisplay();

               // hidepDialog();


                Log.d("tttttt ", String.valueOf(stautasCode));

                // Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<Add_comments_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );

               // hidepDialog();
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



    public void add_comment_Btn_clicked(View view) {


        add_comments_data.setUserName(NameField.getText().toString().trim());
        add_comments_data.setCommentText(messageField.getText().toString().trim());


        add_comments_data.setUserID("1");
        add_comments_data.setPublish("0");
        add_comments_data.setItemID(item_id);





        if (!(add_comments_data.getUserName().isEmpty()))

        {


            if(!(add_comments_data.getCommentText().isEmpty()))
            {



                    SendButton.setEnabled(false);


                    insertCommentsFromAPI(add_comments_data.getItemID(),add_comments_data.getUserID(),add_comments_data.getUserName(),add_comments_data.getCommentText(),add_comments_data.getPublish());


                    NotificationByEmail_Data(add_comments_data.getUserName(),"2",add_comments_data.getCommentText(),item_title,add_comments_data.getItemID(),add_comments_data.getPublish(),customer.getAdmin_Email_Notification(),"قسم العليقات",customer.Admin_Email,customer.Name);
                    Toast.makeText(this, "سيتم إضافة تعليقكم قرايباً ", Toast.LENGTH_LONG).show();
                    finish();

                }

            else
            {
                messageField.requestFocus();
                Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الرسالة", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            NameField.requestFocus();
            Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الاسم", Toast.LENGTH_LONG).show();

        }


    }


    //############################ get data function ############################################
    private void NotificationByEmail_Data(String Name_Field, String phone_type, String Message_Field, String item_title, String item_ID, String Publish, String email_notification, String message_type, String Admin_Email, String customer_name) {
       // showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        NotificationByEmailAPI data1 = retrofit.create(NotificationByEmailAPI.class);




        Call<List<Add_EmailNotification_data>> call = data1.insertEmailNotificationAPI( Name_Field, phone_type,  Message_Field,  item_title,  item_ID, Publish,email_notification, message_type,Admin_Email,customer_name);

        call.enqueue(new Callback<List<Add_EmailNotification_data>>() {
            @Override
            public void onResponse(Call<List<Add_EmailNotification_data>> call, Response<List<Add_EmailNotification_data>> response) {
                // create var
                String d1="";
                int stautasCode = response.code();
                // the data in data_response
                //  responseDataStringtype = response.body();

                //updateDisplay();

               // hidepDialog();


                Log.d("tttttt ", String.valueOf(stautasCode));

                // Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<Add_EmailNotification_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );

               // hidepDialog();
            }
        });

    }
}

