package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
class Package (
    val vendor_id: String="",
    val driver_id: String="",
    val building_id: String="",
    val status: Int =0,
    val items: Array<Item>,
    val start_datetime: LocalDateTime,
    val finish_datetime: LocalDateTime,
    var pacakage_id: String=""): Parcelable
