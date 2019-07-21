package com.mekhedov.sasha.weathermvvm.dagger

import com.mekhedov.sasha.weathermvvm.viewmodel.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface Injector  {
    fun inject(postViewModel: WeatherViewModel)

    @Component.Builder
    interface Builder {
        fun build(): Injector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}