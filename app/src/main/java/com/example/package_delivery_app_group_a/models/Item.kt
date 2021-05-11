package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var item_id: String = "",
    val itemName : String=""): Parcelable
