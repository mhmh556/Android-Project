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
import com.alarayf.alarayf.models.Tree_last_added_list_data;

import java.util.List;

/**
 * Created by Mohammad on 11/8/16.
 */

public class Tree_last_added_Adapter extends ArrayAdapter<Tree_last_added_list_data> {

    Customer customer;

    private Context context;
    private List<Tree_last_added_list_data> Tree_last_name_list;

    public Tree_last_added_Adapter(Context context, int resource, List<Tree_last_added_list_data> objects) {

        //customer = (Customer) getApplicationContext();
        super(context, resource, objects);
        this.context = context;
        this.Tree_last_name_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.row_tree_last_added_list, parent, false);

        }

        // Display Event fileds

        Tree_last_added_list_data tree_last_added_list_data = Tree_last_name_list.get(position);

        TextView tree_name;


        tree_name= (TextView) row.findViewById(R.id.Tree_last_added_name99);
        tree_name.setText(tree_last_added_list_data.getName());


        // change font for row lable

        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),customer.getFont_Name());
        tree_name.setTypeface(mytypeface);

        if (position % 2 == 1) {
            row.setBackgroundColor(Color.rgb(245,245,245));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }

        return row;
    }
}