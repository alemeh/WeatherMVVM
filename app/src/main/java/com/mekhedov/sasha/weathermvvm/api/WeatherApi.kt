package com.mekhedov.sasha.weathermvvm.api

import com.mekhedov.sasha.weathermvvm.model.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface WeatherApi {
    @Headers("X-Yandex-API-Key: b520de87-f1bb-4887-8b7a-105bf30157c0")
    @GET("/v1/informers")
    fun getWeather(@Query("lat") lat: String,
                   @Query("lon") lon: String ): Observable<Weather>
}