package com.example.package_delivery_app_group_a

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.example.package_delivery_app_group_a.ui.driver.DriverMainActivity
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivity

class LaunchActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        Handler().postDelayed(
                {
                    startActivity(Intent(this@LaunchActivity, ManagerMainActivity::class.java))
                    finish()
                },
                1500
        )

    }
}


