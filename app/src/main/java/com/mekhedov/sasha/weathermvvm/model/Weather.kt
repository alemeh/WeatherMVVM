package com.mekhedov.sasha.weathermvvm.model

data class Weather (val now: Int,
                    val now_dt: String,
                    val fact: WeatherFact)

data class WeatherFact (val temp: String)