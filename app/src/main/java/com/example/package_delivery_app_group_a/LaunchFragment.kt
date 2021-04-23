package com.example.package_delivery_app_group_a

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import java.util.*
import kotlin.concurrent.schedule


class LaunchFragment : Fragment() {


    private lateinit var navController: NavController
    var timer = Timer()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        timer.schedule(800) {
            navController.navigate(R.id.nav_login)

        }
    }

}