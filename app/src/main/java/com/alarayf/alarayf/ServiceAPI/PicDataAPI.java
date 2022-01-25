package com.alarayf.alarayf.ServiceAPI;


import com.alarayf.alarayf.models.Pic_list_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammad on 11/25/16.
 */

public interface PicDataAPI {

    @GET("GetPic.php")
    Call<List<Pic_list_data>> getPicListFromAPI();
}
