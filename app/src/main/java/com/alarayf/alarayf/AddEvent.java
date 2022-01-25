package com.alarayf.alarayf;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alarayf.alarayf.ServiceAPI.AddEventAPI;
import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
import com.alarayf.alarayf.models.AddEvent_data;
import com.alarayf.alarayf.models.Add_EmailNotification_data;
import com.google.firebase.analytics.FirebaseAnalytics;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.alarayf.alarayf.ServiceAPI.AddEventAPI;
import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
import com.alarayf.alarayf.models.AddEvent_data;
import com.alarayf.alarayf.models.Add_EmailNotification_data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEvent extends AppCompatActivity  implements View.OnClickListener,View.OnFocusChangeListener {

    Customer customer;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;
    // private List<ٍSuggestions_data> data_response;
    private String responseDataStringtype = "";

    EditText NameField;
    EditText Cell_phoneField;
    EditText dateField;
    EditText messageField;

    TextView PageTitle;
    Button SendButton;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    AddEvent_data addEvent_data = new AddEvent_data();

    Add_EmailNotification_data add_EmailNotification_data = new Add_EmailNotification_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alarayf.alarayf.R.layout.activity_add_event);

        customer = (Customer) getApplicationContext();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("إضافة مناسبة");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(com.alarayf.alarayf.R.id.content_add_event);

        RL.setBackgroundResource(customer.Page_Background );

        NameField = (EditText) findViewById(com.alarayf.alarayf.R.id.Name_us);
        dateField = (EditText) findViewById(com.alarayf.alarayf.R.id.date_us);
        Cell_phoneField = (EditText) findViewById(com.alarayf.alarayf.R.id.Cell_phone_us);
        messageField = (EditText) findViewById(com.alarayf.alarayf.R.id.message_us);

        SendButton = (Button) findViewById(com.alarayf.alarayf.R.id.send);

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(), "Laha.ttf");

        PageTitle = (TextView) findViewById(com.alarayf.alarayf.R.id.pageTilte);


        PageTitle.setTypeface(mytypeface);

        NameField.requestFocus();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        findViewsById();

        setDateTimeField();

    }

    private void findViewsById() {
        dateField = (EditText) findViewById(com.alarayf.alarayf.R.id.date_us);
        dateField.setInputType(InputType.TYPE_NULL);
        //dateField.requestFocus();


    }

    private void setDateTimeField() {
        dateField.setOnClickListener(this);
        dateField.setOnFocusChangeListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateField.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    //############################ Subment data function ############################################

    private void insertContactUsFromAPI(String Name, String message, String date, String CellNum) {
        // check if they is internet
        if (this.isOnline()) {
            get_Data(Name,  message,  date,  CellNum);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }

    //############################ get data function ############################################
    private void get_Data(String Name, String message, String date, String CellNum) {
       // showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        AddEventAPI data1 = retrofit.create(AddEventAPI.class);


        Call<List<AddEvent_data>> call = data1.insertEventFromAPI( Name,  message,  date,  CellNum);

        call.enqueue(new Callback<List<AddEvent_data>>() {
            @Override
            public void onResponse(Call<List<AddEvent_data>> call, Response<List<AddEvent_data>> response) {
                // create var
                String d1 = "";
                int stautasCode = response.code();
                // the data in data_response
                //  responseDataStringtype = response.body();

                //updateDisplay();

               // hidepDialog();

                Bundle bundle = new Bundle();
                //bundle.putString("US", "image");
                mFirebaseAnalytics.logEvent("Event_Inserted", null);

                Log.d("tttttt ", String.valueOf(stautasCode));

                // Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<AddEvent_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage());

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
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //############################ remove html tag function ############################################
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    public void Add_EventBtn_clicked(View view) {

        addEvent_data.setTitle(NameField.getText().toString().trim());
        addEvent_data.setStartdate(dateField.getText().toString().trim());
        addEvent_data.setShortdesc(Cell_phoneField.getText().toString().trim());
        addEvent_data.setDesc(messageField.getText().toString().trim());

        if (!(addEvent_data.getTitle().isEmpty()))

        {


            if (!(addEvent_data.getStartdate().isEmpty())) {


                if (!(addEvent_data.getDesc().isEmpty())) {

                    SendButton.setEnabled(false);
                    insertContactUsFromAPI(addEvent_data.getTitle(), addEvent_data.getDesc(), addEvent_data.getStartdate(), addEvent_data.getShortdesc());

                    Send_Email_Notification();

                    Toast.makeText(this, "تمت الاضافة بنجاح", Toast.LENGTH_LONG).show();

                    finish();

                } else {
                    messageField.requestFocus();
                    Toast.makeText(this, " لا يمكن إلارسال يجب كتابة موضوع الرسالة", Toast.LENGTH_LONG).show();
                }

            } else {
                dateField.requestFocus();
                Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الرسالة", Toast.LENGTH_LONG).show();
            }

        } else {
            NameField.requestFocus();
            Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الاسم", Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onClick(View v) {

        if(v == dateField) {
            fromDatePickerDialog.show();
        }
    }

   @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch(v.getId()){
            case com.alarayf.alarayf.R.id.date_us:
                //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(dateField.getWindowToken(), 0);
                fromDatePickerDialog.show();
                Cell_phoneField.requestFocus();
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



        add_EmailNotification_data.setFrom_Email(customer.Admin_Email);
        add_EmailNotification_data.setFrom_Name(customer.Name);
        add_EmailNotification_data.setTo_Email(customer.Admin_Email_Notification);
        add_EmailNotification_data.setTitle("طلب إضافة مناسبة جديد");

        add_EmailNotification_data.setRow1_1("الأسم الرباعي");
        add_EmailNotification_data.setRow1_2(addEvent_data.getTitle());

        add_EmailNotification_data.setRow2_1("تاريخ المناسبة");
        add_EmailNotification_data.setRow2_2(addEvent_data.getStartdate());

        add_EmailNotification_data.setRow3_1("رقم الجوال");
        add_EmailNotification_data.setRow3_2(addEvent_data.getShortdesc());

        add_EmailNotification_data.setRow4_1(" نوع الجوال ");
        add_EmailNotification_data.setRow4_2("اندرويد");


        add_EmailNotification_data.setRow5_1("عنوان الرسالة");
        add_EmailNotification_data.setRow5_2(addEvent_data.getDesc());




        NotificationByEmail_Data(add_EmailNotification_data.getFrom_Email(),add_EmailNotification_data.getFrom_Name(),
                add_EmailNotification_data.getTo_Email(),add_EmailNotification_data.getTitle(),
                add_EmailNotification_data.getRow1_1(),add_EmailNotification_data.getRow1_2(),
                add_EmailNotification_data.getRow2_1(),add_EmailNotification_data.getRow2_2(),
                add_EmailNotification_data.getRow3_1(),add_EmailNotification_data.getRow3_2(),
                add_EmailNotification_data.getRow4_1(),add_EmailNotification_data.getRow4_2(),
                add_EmailNotification_data.getRow5_1(),add_EmailNotification_data.getRow5_2());

    }
}
