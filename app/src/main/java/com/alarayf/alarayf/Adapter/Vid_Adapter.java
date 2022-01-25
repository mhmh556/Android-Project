package com.alarayf.alarayf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.R;

import com.alarayf.alarayf.models.Vid_list_data;


import java.util.List;

import com.alarayf.alarayf.Customer;

/**
 * Created by Mohammad on 11/25/16.
 */

public class Vid_Adapter extends ArrayAdapter<Vid_list_data> {

    private Context context;
    Customer customer;

    private List<Vid_list_data> vid_list;

    private  String MyVidURL="";
    private  String VidCode="";


    public Vid_Adapter (Context context, int resource, List<Vid_list_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.vid_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_vid, parent, false);

        }





        Vid_list_data vid_list_data = vid_list.get(position);


        TextView Vid_name= (TextView) row.findViewById(com.alarayf.alarayf.R.id.vid_title);

        Vid_name.setText(vid_list_data.getTitle());


        MyVidURL = vid_list_data.getMyVIdeo();
        VidCode = "<html>" +
                "<body>" +
                "<iframe width=\"260\"" +
                " height=\"190\" " +
                "src=\" " + MyVidURL +
                "\" frameborder=\"10\" allowfullscreen></iframe></body></html>";

        WebView vid = (WebView) row.findViewById(com.alarayf.alarayf.R.id.vid_url);
        vid.getSettings().setJavaScriptEnabled(true);
        vid.loadData(VidCode, "text/html", "utf-8");

        // change the font of title of the news
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),customer.getFont_Name());
        Vid_name.setTypeface(mytypeface);




        return row;
    }


}