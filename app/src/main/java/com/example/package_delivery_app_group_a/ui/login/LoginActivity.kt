package com.example.package_delivery_app_group_a.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.driver.DriverMainActivity
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivity
import com.example.package_delivery_app_group_a.ui.register.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
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
                        hideShowProgBar()
                        if (task.isSuccessful) {
                            //val firebaseUser: FirebaseUser = task.result!!.user!!
                            //"You are registered successfully. Your user id is ${firebaseUser.uid}",
                            showErrorSnackBar(resources.getString(R.string.suc_msg_login), false)
                            val intent = Intent(this@LoginActivity, DriverMainActivity::class.java)
                            startActivity(intent)
                            finish()
//                            FirebaseAuth.getInstance().signOut()
//                            finish()
                        } else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    })
        }
    }
}