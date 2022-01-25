package com.alarayf.alarayf.ServiceAPI;



import com.alarayf.alarayf.models.Update_visit_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammad on 11/27/16.
 */

public interface UpdateVisitAPI {



       // @GET("/GetTree_S.php")
       // Call<List<Tree_data>> getTreeListFromAPI();

        //@Multipart
        @FormUrlEncoded
        @POST("Update_visit_count.php")
        Call<List<Update_visit_data>> increase_k2_item_visitFromAPI(@Field("a") String a);

}

