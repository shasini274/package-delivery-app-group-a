package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.package_delivery_app_group_a.R
import com.google.android.material.navigation.NavigationView

class ManagerMainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_manager)
        val toolbar: Toolbar = findViewById(R.id.toolbar_manager)
        setSupportActionBar(toolbar)

/*
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
*/
        val drawerLayout: DrawerLayout = findViewById(R.id.manager_drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view_manager)
        val navController = findNavController(R.id.nav_host_fragment_manager)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_vendor, R.id.nav_building, R.id.nav_driver, R.id.nav_history, R.id.nav_account), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_manager)  //content_main.xml
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}