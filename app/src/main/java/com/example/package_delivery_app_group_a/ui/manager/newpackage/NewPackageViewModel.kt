package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewPackageViewModel : ViewModel() {

/*
    private val _textDriverId = MutableLiveData<String>().apply {
        value = "Select Driver"
    }
*/
    var selectDriverId = MutableLiveData<String>()
    var selectVendorId = MutableLiveData<String>()

    fun seletDriver (driverId: String) {
        selectDriverId.value = driverId
    }
}