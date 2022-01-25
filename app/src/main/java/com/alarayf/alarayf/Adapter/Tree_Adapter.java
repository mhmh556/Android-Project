package com.alarayf.alarayf.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;


import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.R;


import com.alarayf.alarayf.models.News_list_data;
import com.alarayf.alarayf.models.Tree_data;




import java.util.List;

import com.alarayf.alarayf.Customer;

import static com.alarayf.alarayf.R.color.colorPrimary;

/**
 * Created by Mohammad on 11/27/16.
 */

public class Tree_Adapter extends ArrayAdapter<Tree_data> {

    private Context context;
    Customer customer;
    private List<Tree_data> tree_list;




    public Tree_Adapter (Context context, int resource, List<Tree_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.tree_list = objects;
    }

    @SuppressLint("ResourceAsColor")
    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_tree, parent, false);

        }





        // Display News fileds

        Tree_data tree_data = tree_list.get(position);


        ImageView image = (ImageView) row.findViewById(R.id.tree_image);

        image.setImageResource(customer.getEvent_image_Name());

        TextView Tree_name= (TextView) row.findViewById(com.alarayf.alarayf.R.id.tree_name);
        Tree_name.setText(tree_data.getName());


        // change the font of title of the news
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),customer.getFont_Name());
        Tree_name.setTypeface(mytypeface);



       /* if(convertView == null){

            convertView = inflater.inflate(R.layout.row_event_list,null);
        }*/

        if (position % 2 == 1) {
            row.setBackgroundColor(Color.rgb(245,245,245));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }


            // change the text color for dead one on the tree
            Change_Text_Color_For_Dead(tree_data, Tree_name);


        return row;
    }

    private void Change_Text_Color_For_Dead(Tree_data tree_data, TextView tree_name) {
        if(tree_data.getAlive().equals("0")) {

            tree_name.setTextColor(Color.rgb(0,0,0));

        }
        else
        {
            tree_name.setTextColor(Color.rgb(36,145,0));
        }
    }


}