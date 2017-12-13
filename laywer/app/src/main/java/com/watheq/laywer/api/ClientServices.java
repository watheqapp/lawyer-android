package com.watheq.laywer.api;

import com.watheq.laywer.model.CompleteFilesBody;
import com.watheq.laywer.model.CompleteProfileBody;
import com.watheq.laywer.model.LoginBody;
import com.watheq.laywer.model.LoginModelResponse;
import com.watheq.laywer.model.MainCategoriesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by mahmoud.diab on 11/29/2017.
 */

public interface ClientServices {
    String baseUrl = "http://138.197.41.25/watheq/public/";

    @POST("api/lawyer/login")
    Call<LoginModelResponse> loginUser(@Body LoginBody loginBody);

    @POST("api/auth/lawyer/completeProfile")
    Call<LoginModelResponse> completeProfile(@Header("Authorization") String auth, @Body CompleteProfileBody completeProfileBody);

    @POST("api/auth/lawyer/completeFiles")
    Call<LoginModelResponse> completeFiles(@Header("Authorization") String auth, @Body CompleteFilesBody completeFilesBody);

    @GET("api/auth/category/list")
    Call<MainCategoriesResponse> getCategories(@Header("Authorization") String auth);
}
