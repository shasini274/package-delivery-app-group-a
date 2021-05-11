package com.example.package_delivery_app_group_a.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    // Firebase Constants
    // This  is used for the collection name for USERS.
    const val USERS: String = "users"
    const val DRIVERS: String = "drivers"
    const val BUILDINGSITES: String = "buildingsites"
    const val VENDORS: String = "vendors"
    const val PACKAGES: String = "packages"
    const val ITEMS: String = "items"

    const val PKG_APP_PREFERENCES: String = "appPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"

    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 2

    const val ID: String = "id"
    const val FNAME: String = "firstName"
    const val LNAME: String = "lastName"
    const val EMAIL: String = "email"
    const val MOBILE: String = "mobile"
    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"
    const val IMAGE: String = "image"
    const val COMPLETED_PROFILE: String= "profileCompleted"

    const val DRIVER = "driver"
    const val MANAGER = "manager"
    var STATUS = "status"





    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        //c:/user/shasini/pictures/apple.jpg
        //this would give the extension '.jpg'
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}