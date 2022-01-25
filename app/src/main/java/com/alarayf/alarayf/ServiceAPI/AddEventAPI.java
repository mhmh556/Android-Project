package com.alarayf.alarayf.ServiceAPI;

import com.alarayf.alarayf.models.AddEvent_data;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammad on 11/27/16.
 */

public interface AddEventAPI {

    //@Multipart
    @FormUrlEncoded
    @POST("InsertEvent_Json.php")
    Call<List<AddEvent_data>> insertEventFromAPI(@Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d);

}
