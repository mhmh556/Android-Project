package com.alarayf.alarayf.ServiceAPI;


import com.alarayf.alarayf.models.Suggestions_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammad on 11/26/16.
 */

public interface SuggestionsAPI {

    //@Multipart
    @FormUrlEncoded
    @POST("PostS_Json.php")
    Call<List<Suggestions_data>> insertContactUsFromAPI(@Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d, @Field("e") String e);

}
