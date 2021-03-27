package com.online_shop.core;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("login.php")
    Call<Integer> login(@Query("login") String login, @Query("password") String password);

    @GET("register.php")
    Call<Integer> register(
            @Query("name_") String name_,
            @Query("surname") String surname,
            @Query("email") String email,
            @Query("password_") String password_,
            @Query("phonenumber") String phonenumber,
            @Query("address") String address
    );

    @GET("update.php")
    Call<Integer> update(
            @Query("id_") Integer user_id,
            @Query("field") String field,
            @Query("value_") String value);

   // @GET("makeOrder.php")
  //  Call <Integer> makeOrder(@Query("order") Order order);
}