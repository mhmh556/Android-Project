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
import com.alarayf.alarayf.models.News_list_data;
import com.alarayf.alarayf.models.Pic_list_data;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.alarayf.alarayf.Customer;

/**
 * Created by Mohammad on 11/25/16.
 */


/**
 * Created by Mohammad on 11/17/16.
 */

public class Pic_Adapter extends ArrayAdapter<Pic_list_data> {
    private Context context;
    Customer customer;
    private List<Pic_list_data> pic_list;

    private Bitmap bitmap;
    private String NEWS_PATH = "http://www.alarayf.com/GetNews.php";
    private String  IMAGE_PATH = "http://www.alarayf.com/media/k2/items/cache/";

    private  String MyimageURL="";
    // for the image naming
    private String  XS = "_XS.jpg";
    private String  S = "_S.jpg";
    private String  M = "_M.jpg";
    private String  XL = "_XL.jpg";
    private String  L = "_L.jpg";


    public Pic_Adapter (Context context, int resource, List<Pic_list_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.pic_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_pic, parent, false);

        }
        // Display News fileds

        Pic_list_data pic_list_data = pic_list.get(position);

        TextView Pic_name;


        ImageView Pic_image;

        Pic_name = (TextView) row.findViewById(com.alarayf.alarayf.R.id.pic_title);
        Pic_name.setText(pic_list_data.getTitle());

        // image URL
        MyimageURL = IMAGE_PATH + pic_list_data.getImageName() + S;

        Pic_image = (ImageView) row.findViewById(com.alarayf.alarayf.R.id.Pic_image);

        // i have used the Picasso library
        Picasso.get().load(MyimageURL).into(Pic_image);

        // change the font of title of the news
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(), customer.getFont_Name());
        Pic_name.setTypeface(mytypeface);


        return row;
    }


}