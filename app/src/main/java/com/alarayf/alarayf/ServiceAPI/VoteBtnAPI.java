
/**
 * Created by Mohammad on 11/17/16.
 */
package com.alarayf.alarayf.ServiceAPI;

import com.alarayf.alarayf.models.Vote_btn_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface VoteBtnAPI {
    @GET("vote.php")
    Call<List<Vote_btn_data>> getVoteFromAPI();
}

