package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.package_delivery_app_group_a.R
import com.google.android.material.navigation.NavigationView


class MainManagerFragment : Fragment() {

//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
//        val root = inflater.inflate(R.layout.fragment_main_manager, container, false)
//        val toolbar: Toolbar = root.findViewById(R.id.toolbar)
//        toolbar.inflateMenu(R.menu.activity_main_drawer)

//        val drawerLayout: DrawerLayout = root.findViewById(R.id.drawer_layout)
//        val navView: NavigationView = root.findViewById(R.id.nav_view)
//        val navController = root.findViewById(R.id.nav_host_fragment)

//        appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.nav_home, R.id.nav_vendor, R.id.nav_building, R.id.nav_driver, R.id.nav_history, R.id.nav_account), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

/*
        override fun onSupportNavigateUp(): Boolean {
            val navController = root.findNavController(R.id.nav_host_fragment)  //content_main.xml
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
*/
        return inflater.inflate(R.layout.fragment_main_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.activity_main_drawer)
    }


}