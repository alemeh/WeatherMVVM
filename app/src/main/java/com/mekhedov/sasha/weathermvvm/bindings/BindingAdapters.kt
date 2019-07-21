package com.mekhedov.sasha.weathermvvm.bindings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.mekhedov.sasha.weathermvvm.model.Weather

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.context as AppCompatActivity
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableRefresh")
fun setMutableRefresh(view: SwipeRefreshLayout, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.context as AppCompatActivity
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.isRefreshing = if(value==View.VISIBLE) true else false})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.context as AppCompatActivity
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableResult")
fun setMutableResult(view: TextView, model: MutableLiveData<Weather>?) {
    val parentActivity:AppCompatActivity? = view.context as AppCompatActivity
    if(parentActivity != null && model != null) {
        model.observe(parentActivity, Observer { value -> view.text = value?.fact?.temp?:""})
    }
}