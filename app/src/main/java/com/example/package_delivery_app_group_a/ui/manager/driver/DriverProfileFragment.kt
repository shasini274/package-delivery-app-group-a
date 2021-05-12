package com.example.package_delivery_app_group_a.ui.manager.driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingProfileFragmentArgs


class DriverProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profile_driver, container, false)

        val tvFName = root.findViewById<TextView>(R.id.driver_profile_fname)
        val tvLName = root.findViewById<TextView>(R.id.driver_profile_lname)
        val tvNumber = root.findViewById<TextView>(R.id.driver_profile_mobile)
        val tvId = root.findViewById<TextView>(R.id.driver_profile_id)

        val args: DriverProfileFragmentArgs by navArgs()

        tvFName.text = args.driverProFName
        tvLName.text = args.driverProLName
        tvNumber.text = args.driverProMobile.toString()
        tvId.text = args.driverProId


        return root
    }

    fun showPicture() {

    }

}