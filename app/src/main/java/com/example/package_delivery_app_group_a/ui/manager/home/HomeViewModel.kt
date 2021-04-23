package com.example.package_delivery_app_group_a.ui.manager.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "On the way"
    }
    val text: LiveData<String> = _text
}