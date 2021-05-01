package com.example.package_delivery_app_group_a.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.MainActivity
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.ui.driver.DriverMainActivity
import com.example.package_delivery_app_group_a.ui.driver.DriverProfileActivity
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivity
import com.example.package_delivery_app_group_a.ui.register.RegisterActivity
import com.example.package_delivery_app_group_a.utils.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        forgot_pwd.setOnClickListener(this)
        login_register.setOnClickListener(this)
        login.setOnClickListener(this)
//        login_register.setOnClickListener{
//            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//            startActivity(intent)
//
//        }
//        login.setOnClickListener{
//            checkLoginInputs()
//        }
    }

    override fun onClick(view: View?) {
        if ( view != null){
            when (view.id){
                R.id.login_register -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
                R.id.login -> {
                    signInUser()
                }
                R.id.forgot_pwd ->{
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
//    override fun onStart(){
//        super.onStart()
//        val user = FirebaseAuth.getInstance().getCurrentUser()
//        if (user != null)
//        {
//            val intent = Intent(this@LoginActivity, ManagerMainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }}
//        val user: FirebaseUser? = Firebase.auth.currentUser
//        user?.let {
//            Log.d("TAG", user.toString())
//            startActivity(Intent(this, ManagerMainActivity::class.java))
//            showErrorSnackBar(resources.getString(R.string.suc_msg_login), false)
//        }

    private fun checkLoginInputs(email :String, pwd: String): Boolean {

        return when {
            email.isEmpty() && pwd.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }

            pwd.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password), true)
                false
            }

            email.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_email), true)
                false
            }
            else -> {
                true
            }
        }
    }
    private fun signInUser() {
        val email = username.text.trim().toString()
        val pwd = password.text.trim().toString()
        // Check with validate function if the entries are valid or not.
        if (checkLoginInputs(email, pwd)) {
            showProgBar()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
//                        hideShowProgBar()
                        if (task.isSuccessful) {
                            //val firebaseUser: FirebaseUser = task.result!!.user!!
                            //"You are registered successfully. Your user id is ${firebaseUser.uid}",
//                            showErrorSnackBar(resources.getString(R.string.suc_msg_login), false)
//                            val intent = Intent(this@LoginActivity, ManagerMainActivity::class.java)
//                            startActivity(intent)
//                            finish()
                            FirestoreClass().getUserDetails(this@LoginActivity)
//                            FirebaseAuth.getInstance().signOut()
//                            finish()
                        } else {
                            hideShowProgBar()
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    })
        }
    }
    fun userLoggedInSuccess(user: User) {

        // Hide the progress dialog.
        hideShowProgBar()

        // Print the user details in the log as of now.
//        Log.i("First Name: ", user.firstName)
//        Log.i("Last Name: ", user.lastName)
//        Log.i("Email: ", user.email)

        if (user.profileCompleted == 0) {
            // If the user profile is incomplete then launch the UserProfileActivity.
            val intent = Intent(this@LoginActivity, DriverProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        }
        else{
            // Redirect the user to Main Screen after log in.
            //val intent = Intent(this@LoginActivity, DriverProfileActivity::class.java)
            //intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(Intent(this@LoginActivity, DriverMainActivity::class.java))
            finish()
        }

    }
    // END
}