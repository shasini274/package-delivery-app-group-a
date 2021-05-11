package com.example.package_delivery_app_group_a.ui.manager.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.example.package_delivery_app_group_a.utils.GlideLoaderTwo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.nav_header_main.*


class AccountFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
        GlideLoaderTwo(this).loadUserPicture(user.image, user_photo)
        user_name.text = "${user.firstName} ${user.lastName}"
        user_email.text = "${user.email}"
        user_number.text= "${user.mobile}"
    }
    override fun onResume(){
        super.onResume()
        getUserDetails()
    }
}