package com.alarayf.alarayf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;

public class My_lib extends Activity {

    private ProgressDialog pDialog;

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
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService("connectivity");
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //############################ remove html tag function ############################################
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}
