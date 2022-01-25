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
import android.widget.TextView;
import android.widget.Toast;


import com.alarayf.alarayf.ServiceAPI.SuggestionsAPI;

import com.alarayf.alarayf.models.Suggestions_data;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import com.alarayf.alarayf.ServiceAPI.SuggestionsAPI;
import com.alarayf.alarayf.models.Suggestions_data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Suggestions extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;
    // private List<ٍSuggestions_data> data_response;
    private String responseDataStringtype = "";

    EditText NameField;
    EditText EmailField;
    EditText Cell_phoneField;
    EditText TitleField;
    EditText messageField;
    TextView PageTitle;
    Button SendButton;


    Suggestions_data suggestions_data = new Suggestions_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alarayf.alarayf.R.layout.activity_suggestions);
        customer = (Customer) getApplicationContext();



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("App_Need_Update", null);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("الاقتراحات");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NameField = (EditText) findViewById(com.alarayf.alarayf.R.id.Name_us);
        EmailField = (EditText) findViewById(com.alarayf.alarayf.R.id.Email_us);
        Cell_phoneField = (EditText) findViewById(com.alarayf.alarayf.R.id.Cell_phone_us);
        TitleField = (EditText) findViewById(com.alarayf.alarayf.R.id.Subject_us);
        messageField = (EditText) findViewById(com.alarayf.alarayf.R.id.message_us);

        SendButton = (Button) findViewById(com.alarayf.alarayf.R.id.send);

        // change font for the buttons
        Typeface mytypeface = Typeface.createFromAsset(getAssets(), "Laha.ttf");

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

    private void insertContactUsFromAPI(String Name, String Email, String message, String Title, String CellNum) {
        // check if they is internet
        if (this.isOnline()) {
            get_Data(Name, Email, message, Title, CellNum);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }

    //############################ get data function ############################################
    private void get_Data(String Name, String Email, String message, String Title, String CellNum) {
        showpDialog();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        SuggestionsAPI data1 = retrofit.create(SuggestionsAPI.class);


        Call<List<Suggestions_data>> call = data1.insertContactUsFromAPI(Name, Email, message, Title, CellNum);

        call.enqueue(new Callback<List<Suggestions_data>>() {
            @Override
            public void onResponse(Call<List<Suggestions_data>> call, Response<List<Suggestions_data>> response) {
                // create var
                String d1 = "";
                int stautasCode = response.code();
                // the data in data_response
                //  responseDataStringtype = response.body();

                //updateDisplay();

                hidepDialog();


                Log.d("tttttt ", String.valueOf(stautasCode));

                // Log.d("tttttt",  "اعتمد" + stautasCode);
            }

            @Override
            public void onFailure(Call<List<Suggestions_data>> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage());

                hidepDialog();
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

    public void contactUsBtn_clicked(View view) {

        suggestions_data.setName(NameField.getText().toString().trim());
        suggestions_data.setEmail(EmailField.getText().toString().trim());
        suggestions_data.setCell_phone(Cell_phoneField.getText().toString().trim());
        suggestions_data.setTitle(TitleField.getText().toString().trim());
        suggestions_data.setSDesc(messageField.getText().toString().trim());

        if (!(suggestions_data.getName().isEmpty()))

        {


            if (!(suggestions_data.getSDesc().isEmpty())) {


                if (!(suggestions_data.getTitle().isEmpty())) {

                    SendButton.setEnabled(false);
                    insertContactUsFromAPI(suggestions_data.getName(), suggestions_data.getEmail(), suggestions_data.getSDesc(), suggestions_data.getTitle(), suggestions_data.getCell_phone());
                    Toast.makeText(this, "تمت الاضافة بنجاح", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    TitleField.requestFocus();
                    Toast.makeText(this, " لا يمكن إلارسال يجب كتابة موضوع الرسالة", Toast.LENGTH_LONG).show();
                }

            } else {
                messageField.requestFocus();
                Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الرسالة", Toast.LENGTH_LONG).show();
            }

        } else {
            NameField.requestFocus();
            Toast.makeText(this, " لا يمكن إلارسال يجب كتابة الاسم", Toast.LENGTH_LONG).show();

        }


    }
}
