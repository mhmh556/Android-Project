package com.alarayf.alarayf.ServiceAPI;



import com.alarayf.alarayf.models.Comments_list_data;
import com.alarayf.alarayf.models.News_list_data;

import java.util.List;

import com.alarayf.alarayf.models.Comments_list_data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohammad on 11/17/16.
 */

public interface CommentsDataAPI {
    @GET("Get_Comments.php")
    Call<List<Comments_list_data>> getCommentsListFromAPI(@Query("a") String a);


}
