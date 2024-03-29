package com.example.package_delivery_app_group_a.ui.driver

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.package_delivery_app_group_a.BaseActivity
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.utils.GlideLoader
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header_main.*

class DriverMainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_driver)
        val toolbar: Toolbar = findViewById(R.id.toolbar_driver)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.driver_drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view_driver)
        val navController = findNavController(R.id.nav_host_fragment_driver)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home_driver, R.id.nav_history_driver, R.id.nav_account_driver), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_driver)  //content_main.xml
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getUserDetails(){
        showProgBar()
        FirestoreClass().getUserDetails(this)
    }
    fun userDetailSuccess(user: User){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)
        GlideLoader(this).loadUserPicture(user.image, user_img)
        user_text.text = "${user.firstName} ${user.lastName}"
    }
    override fun onResume(){
        super.onResume()
        getUserDetails()
    }
}