package com.alarayf.alarayf.ServiceAPI;

import com.alarayf.alarayf.models.AddEvent_data;
import com.alarayf.alarayf.models.Token_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammad on 11/27/16.
 */

public interface TokenAPI {

    //@Multipart
    @FormUrlEncoded
    @POST("InsertToken.php")
    Call<Token_data> insertTokenFromAPI(@Field("a") String a, @Field("b") String b);

}
