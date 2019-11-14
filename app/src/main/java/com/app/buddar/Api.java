package com.app.buddar;

import com.app.buddar.objects.FAQ;
import com.app.buddar.objects.History;
import com.app.buddar.objects.Login;
import com.app.buddar.objects.Match;
import com.app.buddar.objects.Profile;
import com.app.buddar.objects.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Interface for API
 * Here we init the gets and posts
 */
public interface Api {

    //URL
    String BASE_URL = "http://ec2-3-86-24-174.compute-1.amazonaws.com/";

    /**
     * login user by post
     *
     * @return
     */
    @Headers("Content-Type: application/json")
    @POST("api/login/")
    Call<String> loginUser();

    /**
     * register user by post
     *
     * @param body
     * @return
     */
    @Headers("Content-Type: application/json")
    @POST("/register/")
    Call<User> createUser(@Body String body);

    /**
     * create match by post
     *
     * @param body
     * @return
     */

    @Headers("Content-Type: application/json")
    @POST("/match/")
    Call<Match> createMatch(@Body String body);

    /**
     * profile endpoint
     *
     * @return
     */
    @Headers("Content-Type: application/json")
    @GET("api/profile/")
    Call<String> getProfile();

    /**
     * get FAQ endpoint
     *
     * @return
     */
    @Headers("Content-Type: application/json")
    @GET("/faq/")
    Call<FAQ> getFAQ();

    /**
     * get history endpoint
     *
     * @return
     */
    @Headers("Content-Type: application/json")
    @GET("/history/")
    Call<History> getHistory();

}

