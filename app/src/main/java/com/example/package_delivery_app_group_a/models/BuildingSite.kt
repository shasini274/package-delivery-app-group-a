package com.example.package_delivery_app_group_a.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class BuildingSite (
    val id: String = "",
    val siteName: String="",
    val address: String="",
    val email: String = "",
    val contactPerson: String = "",
    val contactNumber: Long = 0):Parcelable

