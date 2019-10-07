package com.example.chattychat.repositories;

import com.example.chattychat.models.Account;
import com.example.chattychat.models.Channel;
import com.example.chattychat.models.Message;
import com.example.chattychat.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {

    @POST("account/register")
    Observable<Account> createAccount(@Body Account account);

    @POST("account/login")
    Observable<Account> loginAccount(@Body Account account);

    @POST("user/add")
    Observable<User> addUser(@Body User user);

    @POST("channel/add")
    Observable<Channel> addChannel(@Body Channel channel);

    @GET("user/{id}")
    Observable<User> getUserById(@Path("id") String id);

    @GET("user/byEmail/{email}")
    Observable<User> getUserByEmail(@Path("email") String email);

    @GET("channel")
    Observable<Channel> getChannels();

    @GET("message/byChannel/{id}")
    Observable<List<Message>> getMessages(@Path("id") String channelId);

}
