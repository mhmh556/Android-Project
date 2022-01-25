package com.alarayf.alarayf.ServiceAPI;



import com.alarayf.alarayf.models.News_list_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammad on 11/17/16.
 */

public interface NewsDataAPI {
    @GET("GetNews.php")
    Call<List<News_list_data>> getNewsListFromAPI();
}
