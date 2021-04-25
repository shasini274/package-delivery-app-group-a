package com.example.package_delivery_app_group_a.ui.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        reg_login.setOnClickListener(this)
        register.setOnClickListener(this)

//        reg_login.setOnClickListener{
//            onBackPressed()
////            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
////            startActivity(intent)
//
//        }
//        register.setOnClickListener{
//            registerUser()
//        }

    }
    override fun onClick(view: View?) {
        if ( view != null){
            when (view.id){
                R.id.reg_login -> {
                    onBackPressed()
                }
                R.id.register ->{
                    registerUser()
                }
            }
        }
    }
    private fun checkRegisterInputs(fname: String, lname: String,pwd: String,cpwd: String,
                                    email: String): Boolean {
        return when {
            fname.isEmpty() && lname.isEmpty() &&  pwd.isEmpty() &&  cpwd.isEmpty()
                    &&  email.isEmpty()-> {
                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }
            fname.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_first_name), true)
                false
            }
            lname.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_last_name), true)
                false
            }
            pwd.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password), true)
                false
            }
            cpwd.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_cpassword), true)
                false
            }
            email.isEmpty() -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_email), true)
                false
            }
            pwd != cpwd -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_n_cpwd_mismatch), true)
                false
            }
            else -> {
                //showErrorSnackBar(resources.getString(R.string.not_err_details), false)
                true
            }
        }
    }
    private fun registerUser() {
        val fname = reg_fname.text.trim().toString()
        val lname = reg_lname.text.trim().toString()
        val pwd = reg_pwd.text.trim().toString()
        val cpwd = reg_cpwd.text.trim().toString()
        val email = reg_email.text.trim().toString()
        // Check with validate function if the entries are valid or not.
        if (checkRegisterInputs(fname, lname, pwd, cpwd, email)) {
            showProgBar()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        hideShowProgBar()
                        if (task.isSuccessful) {
                            //val firebaseUser: FirebaseUser = task.result!!.user!!
                            //"You are registered successfully. Your user id is ${firebaseUser.uid}",
                            showErrorSnackBar(resources.getString(R.string.not_err_details), false)
                            FirebaseAuth.getInstance().signOut()
                            finish()
                        } else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    })
        }
    }
}