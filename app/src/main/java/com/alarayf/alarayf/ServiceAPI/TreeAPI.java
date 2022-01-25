package com.alarayf.alarayf.ServiceAPI;


import com.alarayf.alarayf.models.AddEvent_data;
import com.alarayf.alarayf.models.Tree_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammad on 11/27/16.
 */

public interface TreeAPI {



       // @GET("/GetTree_S.php")
       // Call<List<Tree_data>> getTreeListFromAPI();

        @FormUrlEncoded
        @POST("GetTree_S_Post.php")

        Call<List<Tree_data>> getTreeListFromAPI(@Field("a") int a);


}
