package com.alarayf.alarayf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.R;
import com.alarayf.alarayf.models.Comments_list_data;
import com.alarayf.alarayf.models.News_list_data;

import java.util.List;

import com.alarayf.alarayf.Customer;
import com.alarayf.alarayf.models.Comments_list_data;

/**
 * Created by Mohammad on 11/17/16.
 */

public class Comments_Adapter extends ArrayAdapter<Comments_list_data> {

    Customer customer;
    private Context context;
    private List<Comments_list_data> comments_list;

    private Bitmap bitmap;
   // private String NEWS_PATH = "http://www.alarayf.com/m20/GetNews.php"; // http://www.alarayf.com/GetNews.php";
   // private String  IMAGE_PATH = "http://www.alarayf.com/media/k2/items/cache/";
   private  String id;

    private String Cons_PATH ;

    public Comments_Adapter(Context context, int resource, List<Comments_list_data> objects, String com_id) {

        super(context, resource, objects);
        this.id = com_id;
        // update to pass the id of the new to fetch the data from comments table
        this.Cons_PATH = customer.getSite_URL()+"Get_Comments.php?a="+id;

        this.context = context;
        this.comments_list = objects;


    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_comments, parent, false);

        }

        // Display  fileds

        Comments_list_data  comments_list_data = comments_list.get(position);

        TextView name;
        TextView Date;
        TextView comment;



       // id = comments_list_data.getId();

        name = (TextView) row.findViewById(com.alarayf.alarayf.R.id.com_Name);
        name.setText(comments_list_data.getUserName());

        Date = (TextView) row.findViewById(com.alarayf.alarayf.R.id.com_date);
        Date.setText(comments_list_data.getCommentDate());

        comment= (TextView) row.findViewById(com.alarayf.alarayf.R.id.com_text);
        comment.setText(comments_list_data.getCommentText());



        if (position % 2 == 1) {
            row.setBackgroundColor(Color.rgb(245,245,245));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }



        return row;
    }


}