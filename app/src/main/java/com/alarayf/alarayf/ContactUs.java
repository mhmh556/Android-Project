package com.alarayf.alarayf;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alarayf.alarayf.ServiceAPI.ContactUsAPI;

import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
import com.alarayf.alarayf.models.Add_EmailNotification_data;
import com.alarayf.alarayf.models.ContactUs_data;
import com.google.firebase.analytics.FirebaseAnalytics;


import java.util.List;

import com.alarayf.alarayf.ServiceAPI.ContactUsAPI;
import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactUs extends AppCompatActivity {

    Customer customer;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;
    private List<ContactUs_data> data_response;
    private String responseDataStringtype="";

    EditText NameField ;
    EditText EmailField;
    EditText Cell_phoneField;
    EditText TitleField;
    EditText messageField;
    TextView PageTitle;
    Button SendButton;


    RadioGroup rg;
    RadioButton rb;


    ContactUs_data contactUs_data = new ContactUs_data();

    Add_EmailNotification_data add_EmailNotification_data = new Add_EmailNotification_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();

        setContentView(com.alarayf.alarayf.R.layout.activity_contact_us);

// set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(com.alarayf.alarayf.R.id.content_contact_us);

        RL.setBackgroundResource(customer.Page_Background );




        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = getIntent().getExtras();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Contact_Us_Inserted", null);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("اتصل بنا");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         NameField = (EditText) findViewById(com.alarayf.alarayf.R.id.Name_us);
         EmailField = (EditText) findViewById(com.alarayf.alarayf.R.id.Email_us);
         Cell_phoneField = (EditText) findViewById(com.alarayf.alarayf.R.id.Cell_phone_us);
         TitleField = (EditText) findViewById(com.alarayf.alarayf.R.id.Subject_us);
         messageField = (EditText) findViewById(com.alarayf.alarayf.R.id.message_us);

         rg = (RadioGroup) findViewById(com.alarayf.alarayf.R.id.gtype);

         SendButton = (Button) findViewById(com.alarayf.alarayf.R.id.send);

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"Laha.ttf");

         PageTitle = (TextView) findViewById(com.alarayf.alarayf.R.id.pageTilte);

        //NameField.setTypeface(mytypeface);
        //EmailField.setTypeface(mytypeface);
        //Cell_phoneField.setTypeface(mytypeface);
        //TitleField.setTypeface(mytypeface);
        //messageField.setTypeface(mytypeface);
        PageTitle.setTypeface(mytypeface);



        NameField.requestFocus();


        if (customer.Cust_number == 2)
        {

            //RadioButton myRadioButton = (RadioButton) findViewById(R.id.);
            //myRadioButton.setVisibility(View.INVISIBLE);

            RadioGroup myRadioGroup = (RadioGroup) findViewById(com.alarayf.alarayf.R.id.gtype);
            myRadioGroup.setVisibility(View.INVISIBLE);


            TextView PageTitle = (TextView) findViewById(com.alarayf.alarayf.R.id.pageTilte);





            if(bundle.get("type").equals("0"))
            {

                RadioButton rb1 = (RadioButton) findViewById(com.alarayf.alarayf.R.id.sug);
                rb1.setChecked(true);

                contactUs_data.setType_id("0") ;
                getSupportActionBar().setTitle("إقتراح");
                PageTitle.setText("إقتراح");
                // setText() on userName
            }
            else if(bundle.get("type").equals("1"))
            {

                RadioButton rb1 = (RadioButton) findViewById(com.alarayf.alarayf.R.id.contact);
                rb1.setChecked(true);

                contactUs_data.setType_id("1") ;
                getSupportActionBar().setTitle("اتصل بنا");
                PageTitle.setText("اتصل بنا");
            }
            else if(bundle.get("type").equals("2"))
            {

                RadioButton rb1 = (RadioButton) findViewById(com.alarayf.alarayf.R.id.addtree);
                rb1.setChecked(true);

                contactUs_data.setType_id("2") ;
                getSupportActionBar().setTitle("إضافة للشجرة");
                PageTitle.setText("إضافة للشجرة");
            }

            // find the radiobutton by returned id
           // rb = (RadioButton) findViewById(rg_id);

            //rb.setEnabled(false);



        }



    }

    //############################ Subment data function ############################################

    private void insertContactUsFromAPI(String Name,String Email, String message,String Title, String CellNum, String mType){
        // check if they is internet
        if(this.isOnline()) {
            get_Data( Name, Email,  message, Title, CellNum, mType);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data(String Name,String Email, String message,String Title, String CellNum, String Type) {
        //showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        ContactUsAPI data1 = retrofit.create(ContactUsAPI.class);




        Call<List<ContactUs_data>> call = data1.insertContactUsFromAPI( Name, Email, message, Title, CellNum,Type);

        call.enqueue(new Callback<List<ContactUs_data>>() {
            @Override
            public void onResponse(Call<List<ContactUs_data>> call, Response<List<ContactUs_data>> response) {
                // create var
                String d1="";
                int stautasCode= response.code();
                // the data in data_response
              //  responseDataStringtype = response.body();

                //updateDisplay();

                //hidepDialog();


            Log.d("tttttt ", String.valueOf(stautasCode));

               // Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<ContactUs_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );

                //hidepDialog();
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

    public void contactUsBtn_clicked(View view) {

        contactUs_data.setName(NameField.getText().toString().trim());
        contactUs_data.setEmail(EmailField.getText().toString().trim());
        contactUs_data.setCell_phone(Cell_phoneField.getText().toString().trim());
        contactUs_data.setTitle(TitleField.getText().toString().trim());
        contactUs_data.setSDesc(messageField.getText().toString().trim());

       checkType();

        if (!(contactUs_data.getName().isEmpty()))

        {


            if(!(contactUs_data.getSDesc().isEmpty()))
            {


                if(!(contactUs_data.getTitle().isEmpty()))
                {

                    SendButton.setEnabled(false);
                    checkType();
                    insertContactUsFromAPI(contactUs_data.getName(),contactUs_data.getEmail(),contactUs_data.getSDesc(),contactUs_data.getTitle(),contactUs_data.getCell_phone(),contactUs_data.getType_id());

                    Send_Email_Notification();

                    Toast.makeText(this, "تمت الاضافة بنجاح", Toast.LENGTH_LONG).show();
                    finish();

                }
                else
                {
                    TitleField.requestFocus();
                    Toast.makeText(this, " لا يمكن إلارسال يجب كتابة موضوع الرسالة", Toast.LENGTH_LONG).show();
                }

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

    public void checkType() {


        int rg_id = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        rb = (RadioButton) findViewById(rg_id);


        String xx = rb.getText().toString().trim();
        switch (xx) {
            case "اتصل بنا":
                contactUs_data.setType_id("0");

                break;
            case "إقتراح":
                contactUs_data.setType_id("1");

                break;
            case "إضافة للشجرة":
                contactUs_data.setType_id("2");

                break;

            default:
                contactUs_data.setType_id("0");
                // Toast.makeText(ContactUs.this,
                //    "no id", Toast.LENGTH_SHORT).show();
                break;
        }

    }
//############################ get data function ############################################
        private void NotificationByEmail_Data(String From_mail, String From_name, String To_Email, String Section_title, String r1_1, String r1_2, String r2_1, String r2_2, String r3_1,String r3_2, String r4_1, String r4_2, String r5_1, String r5_2) {
            // showpDialog();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(customer.Site_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            //create instent from api
            Send_N_EmailAPI data1 = retrofit.create(Send_N_EmailAPI.class);




            Call<Add_EmailNotification_data> call = data1.insertEmailNotificationAPI( From_mail, From_name,  To_Email,  Section_title,  r1_1, r1_2,r2_1, r2_2,r3_1,r3_2,r4_1,r4_2,r5_1,r5_2);

            call.enqueue(new Callback<Add_EmailNotification_data>() {
                @Override
                public void onResponse(Call<Add_EmailNotification_data> call, Response<Add_EmailNotification_data> response) {
                    // create var
                    String d1="";
                    int stautasCode = response.code();
                    // the data in data_response
                    // Email_response.HttpResponse = String.valueOf(response.body());

                    int ResponseHolderNumber = stautasCode;

                    //updateDisplay();

                    // hidepDialog();

                    Log.d("tttttt ", String.valueOf(stautasCode));


                    // Log.d("tttttt",  " اعتمد " + Email_response.HttpResponse);
                }

                @Override
                public void onFailure(Call<Add_EmailNotification_data> call, Throwable t) {

                    //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                    Log.d("tttttt ", " فشل " + t.getMessage() );


                    // hidepDialog();
                }
            });

        }

        private void Send_Email_Notification() {

            String[] messageType = new String[3];

            messageType[0]="رسالة جديدة من إتصل بنا";
            messageType[1]="رسالة جديدة من إقتراح";
            messageType[2]=" طلب جديد إضافة للشجرة";


            add_EmailNotification_data.setFrom_Email(customer.Admin_Email);
            add_EmailNotification_data.setFrom_Name(customer.Name);
            add_EmailNotification_data.setTo_Email(customer.Admin_Email_Notification);
            add_EmailNotification_data.setTitle(messageType[Integer.parseInt(contactUs_data.getType_id().trim())]);

            add_EmailNotification_data.setRow1_1("الأسم الرباعي");
            add_EmailNotification_data.setRow1_2(contactUs_data.getName());

            add_EmailNotification_data.setRow2_1("البريد الإلكتروني");
            add_EmailNotification_data.setRow2_2(contactUs_data.getEmail());

            add_EmailNotification_data.setRow3_1("رقم الجوال");
            add_EmailNotification_data.setRow3_2(contactUs_data.getCell_phone());

            add_EmailNotification_data.setRow4_1("موضوع الرسالة");
            add_EmailNotification_data.setRow4_2(contactUs_data.getTitle());


            add_EmailNotification_data.setRow5_1("عنوان الرسالة");
            add_EmailNotification_data.setRow5_2(contactUs_data.getSDesc());




            NotificationByEmail_Data(add_EmailNotification_data.getFrom_Email(),add_EmailNotification_data.getFrom_Name(),
                    add_EmailNotification_data.getTo_Email(),add_EmailNotification_data.getTitle(),
                    add_EmailNotification_data.getRow1_1(),add_EmailNotification_data.getRow1_2(),
                    add_EmailNotification_data.getRow2_1(),add_EmailNotification_data.getRow2_2(),
                    add_EmailNotification_data.getRow3_1(),add_EmailNotification_data.getRow3_2(),
                    add_EmailNotification_data.getRow4_1(),add_EmailNotification_data.getRow4_2(),
                    add_EmailNotification_data.getRow5_1(),add_EmailNotification_data.getRow5_2());

        }




}

