package com.alarayf.alarayf.ServiceAPI;


import com.alarayf.alarayf.models.Vid_list_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammad on 11/25/16.
 */

public interface VidDataAPI {

    @GET("GetVideo.php")
    Call<List<Vid_list_data>> getVidListFromAPI();
}
