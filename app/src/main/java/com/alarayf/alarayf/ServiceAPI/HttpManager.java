package com.alarayf.alarayf.ServiceAPI;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mohammad on 11/8/16.
 */

public class HttpManager {
    public HttpManager() {
    }

    public static String getData(String uri) {
        BufferedReader reader = null;

        try {
            URL e = new URL(uri);
            HttpURLConnection con = (HttpURLConnection)e.openConnection();
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            String var7 = sb.toString();
            return var7;
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                    return null;
                }
            }

        }

        return null;
    }

    public static String getData(String uri, String userName, String password) {
        BufferedReader reader = null;
        HttpURLConnection con = null;
        byte[] loginBytes = (userName + ":" + password).getBytes();
        StringBuilder loginBuilder = (new StringBuilder()).append("Basic ").append(Base64.encodeToString(loginBytes, 0));

        try {
            URL e = new URL(uri);
            con = (HttpURLConnection)e.openConnection();
            con.addRequestProperty("Authorization", loginBuilder.toString());
            StringBuilder e11 = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                e11.append(line + "\n");
            }

            String var11 = e11.toString();
            return var11;
        } catch (Exception var21) {
            var21.printStackTrace();

            try {
                int e1 = con.getResponseCode();
                Log.d("HttpManager", "HTTP response code: " + e1);
            } catch (IOException var20) {
                var20.printStackTrace();
            }
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                    return null;
                }
            }

        }

        return null;
    }
}
