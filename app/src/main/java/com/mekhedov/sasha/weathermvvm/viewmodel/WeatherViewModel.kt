package com.mekhedov.sasha.weathermvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.mekhedov.sasha.weathermvvm.api.WeatherApi
import com.mekhedov.sasha.weathermvvm.dagger.DaggerInjector
import com.mekhedov.sasha.weathermvvm.dagger.Injector
import com.mekhedov.sasha.weathermvvm.dagger.NetworkModule
import com.mekhedov.sasha.weathermvvm.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject

class WeatherViewModel: ViewModel(){
    @Inject
    lateinit var weatherApi: WeatherApi

    private lateinit var subscription: Disposable
    val result: MutableLiveData<Weather> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<String> = MutableLiveData()

    private val injector: Injector = DaggerInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        injector.inject(this)
        load()
    }

    public fun load()  {
        subscription = weatherApi.getWeather("55.7522200", "37.6155600").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onLoadStart() }
            .doOnTerminate { onLoadFinish() }
            .subscribe({ weather: Weather? ->
                result.value = weather
            }, { error ->
                errorMessage.value = error.message
            })
    }

    private fun onLoadStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onLoadFinish(){
        loadingVisibility.value = View.GONE
    }
}