package com.alarayf.alarayf;

        import android.app.ProgressDialog;
        import android.content.Intent;
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

        import com.alarayf.alarayf.Customer;
        import com.alarayf.alarayf.R;

        import com.alarayf.alarayf.ServiceAPI.AddMobileAPI;
        import com.alarayf.alarayf.ServiceAPI.NotificationByEmailAPI;
        import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
        import com.alarayf.alarayf.models.Add_EmailNotification_data;
        import com.alarayf.alarayf.models.Add_mobile_data;
        import com.google.firebase.analytics.FirebaseAnalytics;


        import java.util.List;

        import com.alarayf.alarayf.ServiceAPI.AddMobileAPI;
        import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
        import com.alarayf.alarayf.models.Add_EmailNotification_data;
        import com.alarayf.alarayf.models.Add_mobile_data;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class AddMobil extends AppCompatActivity {

    Customer customer;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;
    private List<Add_mobile_data> data_response;
    private Add_EmailNotification_data Email_response;

    private int ResponseHolderNumber;

    EditText NameField ;
    EditText CountryField;
    EditText Cell_phoneField;
    EditText TitleField;
    EditText messageField;
    TextView PageTitle;
    Button SendButton;


    RadioGroup rg;
    RadioButton rb;


    Add_mobile_data add_mobile_data = new Add_mobile_data();

    Add_EmailNotification_data add_EmailNotification_data = new Add_EmailNotification_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();

        setContentView(com.alarayf.alarayf.R.layout.activity_add_mobile);

// set the background image
        RelativeLayout RL = (RelativeLayout)findViewById(com.alarayf.alarayf.R.id.content_add_mobile);

        RL.setBackgroundResource(customer.Page_Background );

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("add_mobil_Inserted", null);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("جوال العرايف");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NameField = (EditText) findViewById(com.alarayf.alarayf.R.id.Name_us);

        CountryField = (EditText) findViewById(com.alarayf.alarayf.R.id.Country);
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

    }

    //############################ Subment data function ############################################

    private void insertMobilFromAPI(String Name,String Country, String message,String Title, String CellNum, String mType){
        // check if they is internet
        if(this.isOnline()) {
            get_Data( Name, Country,  message, Title, CellNum, mType);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data(String Name,String country, String message,String Title, String CellNum, String Type) {
       // showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        AddMobileAPI data1 = retrofit.create(AddMobileAPI.class);




        Call<List<Add_mobile_data>> call = data1.insertMobilFromAPI( Name, country, message, Title, CellNum,Type);

        call.enqueue(new Callback<List<Add_mobile_data>>() {
            @Override
            public void onResponse(Call<List<Add_mobile_data>> call, Response<List<Add_mobile_data>> response) {
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
            public void onFailure(Call<List<Add_mobile_data>> call, Throwable t) {

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

    //############################ remove html tag function ############################################
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    public void Mobile_Btn_clicked(View view) {


        add_mobile_data.setName(NameField.getText().toString().trim());
        add_mobile_data.setCountry(CountryField.getText().toString().trim());
        add_mobile_data.setCell_phone(Cell_phoneField.getText().toString().trim());
        add_mobile_data.setTitle(TitleField.getText().toString().trim());
        add_mobile_data.setSDesc(messageField.getText().toString().trim());

        checkType();

        // Email notification setting


        //


        if (!(add_mobile_data.getName().isEmpty()))

        {


            if(!(add_mobile_data.getSDesc().isEmpty()))
            {


                if(!(add_mobile_data.getTitle().isEmpty()))
                {

                    SendButton.setEnabled(false);
                    checkType();
                    insertMobilFromAPI(add_mobile_data.getName(),add_mobile_data.getCountry(),add_mobile_data.getSDesc(),add_mobile_data.getTitle(),add_mobile_data.getCell_phone(),add_mobile_data.getType_id());

                    // send email notification
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
            case "طلب إضافه" :
                add_mobile_data.setType_id("0") ;

                break;
            case "طلب تعديل":
                add_mobile_data.setType_id("1") ;

                break;
            case "طلب حذف":
                add_mobile_data.setType_id("2");

                break;

            default:
                add_mobile_data.setType_id("0");
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

                ResponseHolderNumber = stautasCode;

                //updateDisplay();

               // hidepDialog();

                Log.d("tttttt ", String.valueOf(stautasCode));


                // Log.d("tttttt",  " اعتمد " + Email_response.HttpResponse);
            }

            @Override
            public void onFailure(Call<Add_EmailNotification_data> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );
                Log.d("tttttt "," رقم الرد " + String.valueOf(ResponseHolderNumber));

               // hidepDialog();
            }
        });

    }

    private void Send_Email_Notification() {

         String[] messageType = new String[3];

        messageType[0]="طلب إضافة رقم جوال جديد";
        messageType[1]="طلب تعديل رقم جوال ";
        messageType[2]="طلب حذف جوال ";


        add_EmailNotification_data.setFrom_Email(customer.Admin_Email);
        add_EmailNotification_data.setFrom_Name(customer.Name);
        add_EmailNotification_data.setTo_Email(customer.Admin_Email_Notification);
        add_EmailNotification_data.setTitle(messageType[Integer.parseInt(add_mobile_data.getType_id().trim())]);

        add_EmailNotification_data.setRow1_1("الأسم الرباعي");
        add_EmailNotification_data.setRow1_2(add_mobile_data.getName());

        add_EmailNotification_data.setRow2_1("الدولة");
        add_EmailNotification_data.setRow2_2(add_mobile_data.getCountry());

        add_EmailNotification_data.setRow3_1("رقم الجوال");
        add_EmailNotification_data.setRow3_2(add_mobile_data.getCell_phone());

        add_EmailNotification_data.setRow4_1("عنوان الرسالة");
        add_EmailNotification_data.setRow4_2(add_mobile_data.getTitle());

        add_EmailNotification_data.setRow5_1("نص الرسالة");
        add_EmailNotification_data.setRow5_2(add_mobile_data.getSDesc());



        NotificationByEmail_Data(add_EmailNotification_data.getFrom_Email(),add_EmailNotification_data.getFrom_Name(),
                add_EmailNotification_data.getTo_Email(),add_EmailNotification_data.getTitle(),
                add_EmailNotification_data.getRow1_1(),add_EmailNotification_data.getRow1_2(),
                add_EmailNotification_data.getRow2_1(),add_EmailNotification_data.getRow2_2(),
                add_EmailNotification_data.getRow3_1(),add_EmailNotification_data.getRow3_2(),
                add_EmailNotification_data.getRow4_1(),add_EmailNotification_data.getRow4_2(),
                add_EmailNotification_data.getRow5_1(),add_EmailNotification_data.getRow5_2());

    }

    public void old_message_on_click(View view) {

        Intent intent =  new Intent(AddMobil.this,Mobile_messages.class);
        startActivity(intent);
    }
}

