package com.alarayf.alarayf.ServiceAPI;


import com.alarayf.alarayf.models.Mobile_messages_list_data;
import com.alarayf.alarayf.models.Pic_list_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammad on 11/25/16.
 */

public interface MobileMessagesDataAPI {

    @GET("Get_Mobile_messages.php")
    Call<List<Mobile_messages_list_data>> getMobileListFromAPI();
}
