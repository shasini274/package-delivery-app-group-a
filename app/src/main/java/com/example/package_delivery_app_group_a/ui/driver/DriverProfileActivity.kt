package com.example.package_delivery_app_group_a.ui.driver

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.MainActivity
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.utils.Constants
import com.example.package_delivery_app_group_a.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_driver_profile.*
import kotlinx.android.synthetic.main.activity_login.*
import java.io.IOException

class DriverProfileActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mUserDetails: User
    private var mSelectedImageUri: Uri? = null
    private var mDriverProfileImageUri: String = ""
//    private val number = pro_phone.text.trim().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_profile)
        setSupportActionBar(findViewById(R.id.pro_toolbar))


        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }
        pro_fname.isEnabled = false
        pro_fname.setText(mUserDetails.firstName)

        pro_lname.isEnabled = false
        pro_lname.setText(mUserDetails.lastName)

        pro_email.isEnabled = false
        pro_email.setText(mUserDetails.email)



        pro_photo.setOnClickListener(this)
        pro_btn.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.pro_photo -> {
                    // Here we will check if the permission is already allowed or we need to request for it.
                    // First of all we will check the READ_EXTERNAL_STORAGE permission and if it is not allowed we will request for the same.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {

//                        showErrorSnackBar(resources.getString(R.string.info_permission), false)
                        Constants.showImageChooser(this@DriverProfileActivity)
                    } else {
                        /*Requests permissions to be granted to this application. These permissions
                         must be requested in your manifest, they should not be granted to your app,
                         and they should have protection level*/
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }
                R.id.pro_btn ->{
                    if(checkDriverProfileInputs()){
                        showProgBar()
                        if(mSelectedImageUri != null){
                            FirestoreClass().uploadImageCloudStorage(this, mSelectedImageUri)
                        }
                        else{
                            updateDriverProfileDetails()
                        }
                    }
                }
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showErrorSnackBar(resources.getString(R.string.suc_permission), false)
                Constants.showImageChooser(this@DriverProfileActivity)
            } else {
                //Displaying another toast if permission is not granted
                showErrorSnackBar(resources.getString(R.string.err_permission), true)
            }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        // The uri of selected image from phone storage.
                        mSelectedImageUri = data.data!!
                        GlideLoader(this@DriverProfileActivity).loadUserPicture(
                            mSelectedImageUri!!,
                            pro_photo
                        )
//                        pro_photo.setImageURI(Uri.parse(selectedImageFileUri.toString()))
                    } catch (e: IOException) {
                        e.printStackTrace()
                        showErrorSnackBar(resources.getString(R.string.err_img_sel), true)
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // A log is printed when user close or cancel the image selection.
            Log.e("Request Cancelled", "Image selection cancelled")
        }
    }
    private fun checkDriverProfileInputs(): Boolean{
        val number = pro_phone.text.trim().toString()
        return when {
            // We have kept the user profile picture is optional.
            // The FirstName, LastName, and Email Id are not editable when they come from the login screen.
            // Check if the mobile number is not empty as it is mandatory to enter.
            number.isEmpty()  -> {
                showErrorSnackBar(resources.getString(R.string.err_no_tel), true)
                false
            }
            else -> {
                true
            }
        }
    }
    private fun updateDriverProfileDetails(){
        val number = pro_phone.text.trim().toString()
        val userHashMap = HashMap<String, Any>()
        //number
        if(number.isNotEmpty()){
            userHashMap[Constants.MOBILE] = number.toLong()
        }
        //image url
        if (mDriverProfileImageUri.isNotEmpty()) {
            userHashMap[Constants.IMAGE] = mDriverProfileImageUri
        }
        userHashMap[Constants.COMPLETED_PROFILE] = 1
//        showProgBar()
        FirestoreClass().updateDriverProfileData(this, userHashMap)
//                        showErrorSnackBar("Your details are valid. You can update them.",false)

    }
    fun driverProfileUpdateSuccess() {
        hideShowProgBar()
        showErrorSnackBar(resources.getString(R.string.suc_update), false)
        startActivity(Intent(this@DriverProfileActivity, DriverMainActivity::class.java))
        finish()
    }
    fun imageUploadSuccess(imageUri: String) {
//        hideShowProgBar()
        mDriverProfileImageUri = imageUri
        updateDriverProfileDetails()
//        Toast.makeText(
//            this@DriverProfileActivity,
//            "Your image is uploaded successfully. Image URL is $imageUri",
//            Toast.LENGTH_SHORT
//        ).show()
    }
//        showErrorSnackBar(resources.getString(R.string.suc_update), false)
//        startActivity(Intent(this@DriverProfileActivity, DriverMainActivity::class.java))
//        finish()

}