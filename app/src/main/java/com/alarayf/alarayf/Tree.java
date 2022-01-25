package com.alarayf.alarayf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alarayf.alarayf.Adapter.News_Adapter;
import com.alarayf.alarayf.Adapter.Tree_Adapter;
import com.alarayf.alarayf.ServiceAPI.NewsDataAPI;
import com.alarayf.alarayf.ServiceAPI.TreeAPI;
import com.alarayf.alarayf.models.AddEvent_data;
import com.alarayf.alarayf.models.News_list_data;
import com.alarayf.alarayf.models.Tree_data;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.alarayf.alarayf.ServiceAPI.TreeAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tree extends AppCompatActivity {

    Customer customer;

    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog pDialog;

    private List<Tree_data> data_response;

    Stack Tree_Node = new Stack();
    ArrayList <String> FullName = new ArrayList<>();

    // Becuase we want to share this with Event detail activity
    public static final String Tree_Name = "Tree_Name";
    public static final String Tree_Parent_id = "Tree_Parent_id";

    ///######################## onCreate ######################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customer = (Customer) getApplicationContext();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        //bundle.putString("US", "image");
        mFirebaseAnalytics.logEvent("Tree_Screen", null);
        //*************** Layout ***********************

        setContentView(com.alarayf.alarayf.R.layout.activity_tree);




        //*************** Full name update ***********************

        if (customer.Cust_number == 1)
        {
            FullName.add(" من قبيلة مطير الغطفانية ");
            FullName.add(" (بني عبدالله) ");
            FullName.add(" عباد");
            FullName.add(" مزغت بن");
            FullName.add(" كويمل بن");
            FullName.add(" مامون بن");
            FullName.add(" صريد بن");
            FullName.add(" مشيب بن");
            FullName.add(" سرحان بن");
            FullName.add(" سليمان بن");
        }
        else if (customer.Cust_number == 2)
        {
            FullName.add(" من قبيلة مطير الغطفانية ");
            FullName.add(" عباد من بني عبدالله من غطفان ");
            FullName.add(" مزغت بن ");
            FullName.add(" كويمل بن ");
            FullName.add(" ميمون بن");
            FullName.add(" غريب بن");
            FullName.add(" ناصر بن");
            FullName.add(" مبارك الأصقع بن");
            FullName.add(" مبارك العرافه بن براك بن");


        }

        String TempString = "";

        for (int i=FullName.size()-1;i>=0;i--)
        {
            TempString+=FullName.get(i);

        }
        TextView NodeNameLable = (TextView) findViewById(com.alarayf.alarayf.R.id.NodeNameLable);

        NodeNameLable.setText(TempString);

        NodeNameLable.setBackgroundColor(Color.rgb(230,230,230));
        //*************** Progress bar ***********************

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("جاري التحميل...");
        pDialog.setCancelable(false);

        //*************** Tool  bar ***********************

        Toolbar toolbar = (Toolbar) findViewById(com.alarayf.alarayf.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(customer.getTree_Name());
        //*************** floating button ***********************



        //*************** back button ***********************

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //*************** get data ***********************

        getTreeListFromAPI(1);


        //*************** prosses selected item ***********************
        // when item get selected navigate to detial view

        ListView mylist = (ListView) findViewById(com.alarayf.alarayf.R.id.mylist);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Intent intent = new Intent(News.this, News_details.class);

                Tree_data tree_data = data_response.get(position);


                Tree_Node.push(Integer.parseInt(tree_data.getParent_id()));
                getTreeListFromAPI(Integer.parseInt(tree_data.getId()));


                //  *************** Full name update ***********************
                FullName.add( tree_data.getName() + "  بن " );


                String TempString = "";



                for (int i=FullName.size()-1;i>=0;i--)
                {
                    TempString+=FullName.get(i);

                }
                TextView NodeNameLable = (TextView) findViewById(com.alarayf.alarayf.R.id.NodeNameLable);

                NodeNameLable.setText(TempString);

            }
        });

    }
    ///############################ updateDisplay function ############################################

    protected void updateDisplay() {
        Tree_Adapter adapter = new Tree_Adapter(this, com.alarayf.alarayf.R.layout.row_tree, this.data_response);
        ListView lv = (ListView) findViewById(com.alarayf.alarayf.R.id.mylist);
        lv.setAdapter(adapter);

        //this.setListAdapter(adapter);
    }

    //############################ getTreeListFromAPI function ############################################

    private void getTreeListFromAPI(int Parent_id){
        // check if they is internet
        if(this.isOnline()) {
            get_Data(Parent_id);
        } else {
            Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
        }

    }
    //############################ get data function ############################################
    private void get_Data(int Parent_id) {
        showpDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create instent from api
        TreeAPI data1 = retrofit.create(TreeAPI.class);

        //Call<List<Tree_data>> call = data1.getTreeListFromAPI();
        Call<List<Tree_data>> call = data1.getTreeListFromAPI( Parent_id);

        call.enqueue(new Callback<List<Tree_data>>() {
            @Override
            public void onResponse(Call<List<Tree_data>> call, Response<List<Tree_data>> response) {
                // create var
                String d1="";
                int stautasCode = response.code();
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
                Log.d("tttttt ", String.valueOf(stautasCode));

            }

            @Override
            public void onFailure(Call<List<Tree_data>> call, Throwable t) {
                hidepDialog();
                Log.d("tttttt ", " فشل " + t.getMessage());
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

    public void BackTreeBtnClicked(View view) {

        if(Tree_Node.isEmpty())
        {

            finish();
        }
        else {
            int x = Integer.parseInt(Tree_Node.pop().toString());
            getTreeListFromAPI(x);





        //  *************** Full name update ***********************
            FullName.remove(FullName.size()-1);
            String TempString = "";

            for (int i=FullName.size()-1;i>=0;i--)
            {
                TempString+=FullName.get(i);

            }
            TextView NodeNameLable = (TextView) findViewById(com.alarayf.alarayf.R.id.NodeNameLable);

            NodeNameLable.setText(TempString);
        }

    }


    public void AddTreeBtnClicked(View view) {

        Intent intent =  new Intent(Tree.this,ContactUs.class);
        intent.putExtra("type","2");
        startActivity(intent);
    }

    public void showHirBtnClicked(View view) {

        Intent intent =  new Intent(Tree.this,Hierarchy_Tree.class);

        startActivity(intent);
    }


    public void NewTreeBtnClicked(View view) {

        Intent intent =  new Intent(Tree.this,Tree_last_added_List.class);
        intent.putExtra("type","2");
        startActivity(intent);
    }
}