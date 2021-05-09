package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Driver (
    val id: String = "",
    val firstName: String="",
    val lastName: String=""): Parcelable