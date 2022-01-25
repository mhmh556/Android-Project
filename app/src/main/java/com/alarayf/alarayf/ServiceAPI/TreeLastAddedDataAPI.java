package com.alarayf.alarayf.ServiceAPI;

import com.alarayf.alarayf.models.Event_list_data;
import com.alarayf.alarayf.models.Tree_last_added_list_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Mohammad on 11/8/16.
 */

public interface TreeLastAddedDataAPI {

 @GET("GetTree_Last_Inserted_json.php")
    Call<List<Tree_last_added_list_data>> getTreeLastAddedListFromAPI();
}
