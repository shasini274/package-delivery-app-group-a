package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.time.LocalDateTime

@Parcelize
class Package (
    val vendor_id: String="",
    val vendorName: String="",
    val driver_id: String="",
    val driverName: String="",
    val building_id: String="",
    val buildingName: String="",
    val status: Int =0,
    val items: @RawValue HashMap<String, Any> = HashMap(),
    val start_datetime: String="",
    val finish_datetime: String="",
    var pacakage_id: String=""): Parcelable
//val items: HashMap<String, Any> = HashMap(name, 0),