package com.alarayf.alarayf;

/**
 * Created by Mohammad on 1/5/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

/**
 * Created by filipp on 5/23/2016.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    Customer customer;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

      // showNotification(remoteMessage.getData().get("body"),remoteMessage.getData().get("badge"));
      showNotification(remoteMessage.getNotification().getBody(),"1");

//Displaying data in log
        //It is optional
       // Log.d(TAG, "Notification Message TITLE: " + remoteMessage.getNotification().getTitle());
       Log.d(TAG, "Notification Message BODY: " + remoteMessage.getNotification().getBody());
       // Log.d(TAG, "Notification Message DATA: " + remoteMessage.getData().toString());
//Calling method to generate notification
       // sendNotification(remoteMessage.getNotification().getTitle(),
               // remoteMessage.getNotification().getBody(), remoteMessage.getData());


    }

    private void showNotification(String message,String badgeNumber) {

        customer = (Customer)getApplicationContext();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);

        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        //
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(customer.getName())
                .setContentText(message) // show message
                .setBadgeIconType(Integer.parseInt(badgeNumber)) // set Badge number
                .setSound(soundUri) // set sound for incoming notification
                .setSmallIcon(customer.Notification_icon)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());
    }


}