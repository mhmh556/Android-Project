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
import com.alarayf.alarayf.models.Cons_list_data;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.alarayf.alarayf.Customer;

/**
 * Created by Mohammad on 11/17/16.
 */

public class Cons_Adapter extends ArrayAdapter<Cons_list_data> {
    Customer customer;

    private Context context;
    private List<Cons_list_data> cons_list;

    private Bitmap bitmap;
    //private String Cons_PATH = "http://www.alarayf.com/m20/GetNews.php"; // http://www.alarayf.com/GetNews.php";
    //private String  IMAGE_PATH = "http://www.alarayf.com/media/k2/items/cache/";

    private String Cons_PATH = customer.getSite_URL()+"GetNews.php";

    private String  IMAGE_PATH = customer.getServer_image_Path();

    private  String MyimageURL="";
    // for the image naming
    private String  XS = "_XS.jpg";
    private String  S = "_S.jpg";
    private String  M = "_M.jpg";
    private String  XL = "_XL.jpg";
    private String  L = "_L.jpg";



    public Cons_Adapter(Context context, int resource, List<Cons_list_data> objects) {

        super(context, resource, objects);

        this.context = context;
        this.cons_list = objects;
    }

    @Override  // the getview method get called multible time, it depend on how many row in the list
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        String Cons_id;
        row = convertView;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(com.alarayf.alarayf.R.layout.row_cons, parent, false);

        }





        // Display cons fileds

        Cons_list_data cons_list_data = cons_list.get(position);

        TextView Cons_name;
        TextView Cons_hits;
        TextView Cons_created;

        TextView Cons_desc;
        ImageView Cons_image;

        Cons_id = cons_list_data.getId();

        Cons_name= (TextView) row.findViewById(com.alarayf.alarayf.R.id.cons_title);
        Cons_name.setText(cons_list_data.getTitle());

        Cons_hits= (TextView) row.findViewById(com.alarayf.alarayf.R.id.hits);
        Cons_hits.setText("الزيارات: " + cons_list_data.getHits());

        Cons_created= (TextView) row.findViewById(com.alarayf.alarayf.R.id.created);
        Cons_created.setText(cons_list_data.getCreated());

        MyimageURL = IMAGE_PATH+cons_list_data.getImageName()+XS;
        //bitmap = getBitmapFormURL(MyimageURL);
        Cons_image = (ImageView) row.findViewById(com.alarayf.alarayf.R.id.cons_image);
        //Cons_image.setImageBitmap(bitmap);
        // i have used the Picasso library
        Picasso.get().load(MyimageURL).into(Cons_image);


        // change the font of title of the Cons
        Typeface mytypeface = Typeface.createFromAsset(context.getAssets(),"Laha.ttf");
        Cons_name.setTypeface(mytypeface);


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