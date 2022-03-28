package ru.samsung.itschool.mdev.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://umorili.herokuapp.com/api/get?site=anekdot.ru&num=5&name=new%20anekdot

public interface AnekdotAPI {

    @GET("/api/get")
    Call<ArrayList<Anekdot>> getAnekdots(@Query("site") String site,
                                         @Query("num") Integer num,
                                         @Query("name") String name);
}
