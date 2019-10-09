package com.example.chattychat.repositories;

import com.example.chattychat.models.Account;
import com.example.chattychat.models.Channel;
import com.example.chattychat.models.Message;
import com.example.chattychat.models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("account/register")
    Call<ResponseBody> createAccount(@Body Account account);

    @POST("account/login")
    Call<Account> login(@Body Account account);

    @POST("user/add")
    Call<User> addUser(@Body User user);

    @POST("channel/add")
    Call<Channel> addChannel(@Body Channel channel);

    @GET("user/{id}")
    Call<User> getUserById(@Path("id") String id);

    @GET("user/byEmail/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @GET("channel")
    Call<Channel> getChannels();

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVkOGRkMGI1MjIwNjMzMDAxNzdlZTRlMyIsImlhdCI6MTU2OTc0NTA0OCwiZXhwIjoxNTc3NTIxMDQ4fQ.goyWNXZmwvWeHKDWy_5Ay41-ij-kWKm7piCnt73clMI")
    @GET("message/byChannel/{id}")
    Call<ArrayList<Message>> getMessages(@Path("id") String channelId);

}
