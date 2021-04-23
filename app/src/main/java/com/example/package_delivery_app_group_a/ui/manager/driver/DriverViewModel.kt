package com.example.package_delivery_app_group_a.ui.manager.driver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class DriverViewModel : ViewModel() {
private val _text = MutableLiveData<String>().apply {
    value = "This is driver Fragment"
}
val text: LiveData<String> = _text
}