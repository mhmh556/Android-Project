
 package com.alarayf.alarayf.ServiceAPI;

/**
 * Created by Mohammad on 11/26/16.
 */

        import com.alarayf.alarayf.models.Add_mobile_data;

        import java.util.List;

        import retrofit2.http.Field;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.Call;
        import retrofit2.http.POST;

public interface AddMobileAPI {

    //@Multipart
    @FormUrlEncoded
    @POST("PostM.php")
    Call<List<Add_mobile_data>> insertMobilFromAPI(@Field("a") String a, @Field("b") String b, @Field("c") String c, @Field("d") String d, @Field("e") String e, @Field("f") String f);

}
