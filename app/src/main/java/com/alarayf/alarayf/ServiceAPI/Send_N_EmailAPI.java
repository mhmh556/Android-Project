
 package com.alarayf.alarayf.ServiceAPI;

/**
 * Created by Mohammad on 11/26/16.
 */

 import com.alarayf.alarayf.models.Add_EmailNotification_data;

 import retrofit2.Call;
 import retrofit2.http.Field;
 import retrofit2.http.FormUrlEncoded;
 import retrofit2.http.POST;

 public interface Send_N_EmailAPI {

     //@Multipart
     @FormUrlEncoded
     @POST("send_Email_Notification.php")
    // Call<List<Add_EmailNotification_data>> insertEmailNotificationAPI(@Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d, @Field("e") String e, @Field("f") String f, @Field("g") String g, @Field("h") String h, @Field("i") String i, @Field("j") String j,@Field("k") String k,@Field("l") String l,@Field("m") String m,@Field("n") String n);
     Call<Add_EmailNotification_data> insertEmailNotificationAPI(@Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d, @Field("e") String e, @Field("f") String f, @Field("g") String g, @Field("h") String h, @Field("i") String i, @Field("j") String j,@Field("k") String k,@Field("l") String l,@Field("m") String m,@Field("n") String n);

 }
