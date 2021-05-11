package com.example.package_delivery_app_group_a.ui.driver.account

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.ui.driver.DriverProfileActivity
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.example.package_delivery_app_group_a.utils.Constants
import com.example.package_delivery_app_group_a.utils.GlideLoaderThree
import com.example.package_delivery_app_group_a.utils.GlideLoaderTwo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_driver_profile.*
import kotlinx.android.synthetic.main.activity_driver_profile.pro_photo
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.user_email
import kotlinx.android.synthetic.main.fragment_account.user_name
import kotlinx.android.synthetic.main.fragment_account.user_number
import kotlinx.android.synthetic.main.fragment_account.user_photo
import kotlinx.android.synthetic.main.fragment_account_driver.*

class DriverAccountFragment : BaseFragment() {
    private lateinit var mUserDetails: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_driver, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        driver_btn_logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        driver_edit_profile.setOnClickListener{
            val intent = Intent(context, DriverProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, mUserDetails)
            startActivity(intent)
        }
    }
    private fun getUserDetails(){
        showProgBar()
        FirestoreClass().getUserDetailsinFragments(this)
    }
    fun userDetailSuccess(user: User){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)
        GlideLoaderThree(this).loadUserPicture(user.image, driver_user_photo)
        driver_user_name.text = "${user.firstName} ${user.lastName}"
        driver_user_email.text = "${user.email}"
        driver_user_number.text= "${user.mobile}"
    }
    override fun onResume(){
        super.onResume()
        getUserDetails()
    }
}