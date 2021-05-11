package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    val id: String = "",
    val firstName: String="",
    val lastName: String="",
    val email: String = "",
    val role: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val profileCompleted: Int =0):Parcelable

