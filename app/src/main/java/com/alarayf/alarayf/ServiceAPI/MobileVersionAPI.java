package com.alarayf.alarayf.ServiceAPI;



        import com.alarayf.alarayf.models.Cons_list_data;
        import com.alarayf.alarayf.models.MobileVersion_data;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

/**
 * Created by Mohammad on 11/17/16.
 */

public interface MobileVersionAPI {
    @GET("GetMobileVersionAndroid.php")
    Call<List<MobileVersion_data>> getMobileVersionFromAPI();
}

