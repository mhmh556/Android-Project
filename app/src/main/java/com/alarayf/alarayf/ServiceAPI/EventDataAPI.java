package com.alarayf.alarayf.ServiceAPI;

import com.alarayf.alarayf.models.Event_list_data;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;



/**
 * Created by Mohammad on 11/8/16.
 */

public interface EventDataAPI {

 @GET("get.php")
    Call<List<Event_list_data>> getEventListFromAPI();
}
