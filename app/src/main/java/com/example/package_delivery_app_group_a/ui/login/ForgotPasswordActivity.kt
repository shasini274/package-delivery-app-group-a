package com.example.package_delivery_app_group_a.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        fgt_submit.setOnClickListener{
            val email = fgt_email.text.trim().toString()
            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_email), true)
            } else {
                showProgBar()
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        hideShowProgBar()
                        if (task.isSuccessful) {
                            showErrorSnackBar(resources.getString(R.string.suc_msg_email_sent), true)
//                             Toast.makeText(this@ForgotPasswordActivity,resources.getString(R.string.suc_msg_email_sent),
//                                Toast.LENGTH_LONG
                            val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }
    }
}