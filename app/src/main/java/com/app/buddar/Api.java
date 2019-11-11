package com.app.buddar;

import com.app.buddar.objects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Interface for API
 * Here we init the gets and posts
 */
public interface Api {

    //URL
    String BASE_URL = "https://control.uey.mx/";

    /**
     * login user by post
     *
     * @param body
     * @return
     */
    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<User> loginUser(@Body String body);

}

