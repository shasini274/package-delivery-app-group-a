package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Vendor (
    val vendorName: String="",
    val address: String="",
    val email: String = "",
    val contactPerson: String = "",
    val contactNumber: Long = 0,
//    val item: String="",
    var vendor_id: String = ""): Parcelable
