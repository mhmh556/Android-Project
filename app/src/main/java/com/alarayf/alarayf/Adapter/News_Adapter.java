package com.alarayf.alarayf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


import com.alarayf.alarayf.models.News_list_data;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.alarayf.alarayf.Customer;

/**
 * Created by Mohammad on 11/17/16.
 */

public class News_Adapter extends ArrayAdapter<News_list_data> {
    Customer customer;
    private Context context;
    private List<News_list_data> news_list;

    private Bitmap bitmap;
   // private String NEWS_PATH = "http://www.alarayf.com/m20/GetNews.php"; // http://www.alarayf.com/GetNews.php";
   // private String  IMAGE_PATH = "http://www.alarayf.com/media/k2/items/cache/";

    private String Cons_PATH = customer.getSite_URL()+"GetNews.php";

    private String  IMAGE_PATH = customer.getServer_image_Path();

    private  String MyimageURL="";
    // for the image naming
    private String  XS = "_XS.jpg";
    private String  S = "_S.jpg";
    private String  M = "_M.jpg";
    private String  XL = "_XL.jpg";
    private String  L = "_L.jpg";


    public News_Adapter (Context context, int resource, List<News_list_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.news_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        String News_id;
        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_news, parent, false);

        }





        // Display News fileds

        News_list_data news_list_data = news_list.get(position);

        TextView News_name;
        TextView News_hits;
        TextView News_created;

        TextView News_desc;
        //TextView Event_Remain;
        //TextView Event_Date;
        ImageView News_image;

        News_id = news_list_data.getId();

        News_name= (TextView) row.findViewById(com.alarayf.alarayf.R.id.news_title);
        News_name.setText(news_list_data.getTitle());

        News_hits= (TextView) row.findViewById(com.alarayf.alarayf.R.id.hits);
        News_hits.setText("الزيارات: " + news_list_data.getHits());

        News_created= (TextView) row.findViewById(com.alarayf.alarayf.R.id.created);
        News_created.setText(news_list_data.getCreated());

        MyimageURL = IMAGE_PATH+news_list_data.getImageName()+XS;
        //bitmap = getBitmapFormURL(MyimageURL);
        News_image = (ImageView) row.findViewById(com.alarayf.alarayf.R.id.news_image);
        //News_image.setImageBitmap(bitmap);
        // i have used the Picasso library
        Picasso.get().load(MyimageURL).into(News_image);

        // change the font of title of the news
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),customer.getFont_Name());
        News_name.setTypeface(mytypeface);


        // to chnage background image for the label
        ImageView imageview  = (ImageView) row.findViewById(com.alarayf.alarayf.R.id.news_image);

        imageview.setBackgroundResource(customer.getEvent_image_Name());
       /* if(convertView == null){

            convertView = inflater.inflate(R.layout.row_event_list,null);
        }*/

        if (position % 2 == 1) {
            row.setBackgroundColor(Color.rgb(245,245,245));
        } else {
            row.setBackgroundColor(Color.WHITE);
        }



        return row;
    }

    public  Bitmap getBitmapFormURL(String src){

        try {
            URL url = new  URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap mybitmap = BitmapFactory.decodeStream(input);
            return  mybitmap;
        } catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }
}