package com.alarayf.alarayf;



/**
 * Created by Mohammad on 1/5/17.
 */

import com.alarayf.alarayf.ServiceAPI.Send_N_EmailAPI;
import com.alarayf.alarayf.ServiceAPI.TokenAPI;
import com.alarayf.alarayf.models.Add_EmailNotification_data;
import com.alarayf.alarayf.models.Token_data;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by filipp on 5/23/2016.
 */



/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.util.Log;

//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    Customer customer;

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]
    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token)
    {
        // TODO: Implement this method to send token to your app server.
        customer = (Customer)getApplicationContext();

        // send token to my server
        Send_Token(token,"Android");
    }




    //############################ get data function ############################################
    private void Send_Token(String token, String device) {
        // showpDialog();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customer.Site_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //create instent from api
        TokenAPI data1 = retrofit.create(TokenAPI.class);





        Call<Token_data> call = data1.insertTokenFromAPI(token,device);

        call.enqueue(new Callback<Token_data>() {
            @Override
            public void onResponse(Call<Token_data> call, Response<Token_data> response) {
                // create var
                String d1="";
                int stautasCode = response.code();
                // the data in data_response
                // Email_response.HttpResponse = String.valueOf(response.body());

                int ResponseHolderNumber = stautasCode;

                //updateDisplay();

                // hidepDialog();

                Log.d("tttttt ", String.valueOf(stautasCode));


                // Log.d("tttttt",  " اعتمد " + Email_response.HttpResponse);
            }

            @Override
            public void onFailure(Call<Token_data> call, Throwable t) {

                //Toast.makeText(this, "فشل في الاتصال بالانترنت", Toast.LENGTH_LONG).show();
                Log.d("tttttt ", " فشل " + t.getMessage() );


                // hidepDialog();
            }
        });

    }
}
/*

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        //registerToken(token); // if we need our server to send a message
    }

    private void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://alarayf.com/firebase/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/