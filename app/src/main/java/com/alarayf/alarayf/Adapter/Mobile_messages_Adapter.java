package com.alarayf.alarayf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.R;
import com.alarayf.alarayf.models.Mobile_messages_list_data;
import com.alarayf.alarayf.models.Pic_list_data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mohammad on 11/25/16.
 */


/**
 * Created by Mohammad on 11/17/16.
 */

public class Mobile_messages_Adapter extends ArrayAdapter<Mobile_messages_list_data> {
    private Context context;
    Customer customer;

    private List<Mobile_messages_list_data> Mobile_list;


    public Mobile_messages_Adapter(Context context, int resource, List<Mobile_messages_list_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.Mobile_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.row_mobile_mssages, parent, false);

        }
        // Display News fileds

        Mobile_messages_list_data mobile_list_data = Mobile_list.get(position);

        TextView message_date;
        TextView message;


        message_date = (TextView) row.findViewById(R.id.message_date);
        message_date.setText(mobile_list_data.getM_date());

        message = (TextView) row.findViewById(R.id.mobile_massage);
        message.setText(mobile_list_data.getMessage());

        // change the font of title of the news
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(), customer.getFont_Name());
        message_date.setTypeface(mytypeface);

        return row;
    }


}