package com.alarayf.alarayf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.R;
import com.alarayf.alarayf.models.Event_list_data;

import java.util.List;

import com.alarayf.alarayf.Customer;

/**
 * Created by Mohammad on 11/8/16.
 */

public class Event_Adapter extends ArrayAdapter<Event_list_data> {

    Customer customer;



    private Context context;
    private List<Event_list_data> event_list;



    public Event_Adapter(Context context, int resource, List<Event_list_data> objects) {

        //customer = (Customer) getApplicationContext();
        super(context, resource, objects);
        this.context = context;
        this.event_list = objects;
    }


    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {




        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_event_list, parent, false);

        }




        // Display Event fileds

        Event_list_data event_list_data = event_list.get(position);

        TextView Event_name;
        TextView Event_Remain;
        TextView Event_Date;
        ImageView Event_image;

        Event_name= (TextView) row.findViewById(com.alarayf.alarayf.R.id.Event_name99);
        Event_name.setText(event_list_data.getTitle());


        Event_Remain= (TextView) row.findViewById(com.alarayf.alarayf.R.id.Event_Remain);
        Event_Remain.setText(event_list_data.getRemain());

        Event_Date= (TextView) row.findViewById(com.alarayf.alarayf.R.id.Event_Date);
        Event_Date.setText(event_list_data.getStartdate());

       /* if(convertView == null){

            convertView = inflater.inflate(R.layout.row_event_list,null);
        }*/

        // change font for row lable

        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),customer.getFont_Name());
        Event_name.setTypeface(mytypeface);


        // to chnage background image for the label
        ImageView imageview  = (ImageView) row.findViewById(com.alarayf.alarayf.R.id.Event_image);

        imageview.setBackgroundResource(customer.getEvent_image_Name());

        if (position % 2 == 1) {
            row.setBackgroundColor(Color.rgb(245,245,245));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }



        return row;
    }
}