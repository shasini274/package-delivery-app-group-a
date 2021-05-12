package com.example.package_delivery_app_group_a.ui.manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManagerMainActivityViewModel: ViewModel() {
    var selectDriverId = MutableLiveData<String>()
    var selectVendorId = MutableLiveData<String>()

}