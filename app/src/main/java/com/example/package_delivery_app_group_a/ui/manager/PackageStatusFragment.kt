package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
//import com.example.package_delivery_app_group_a.ui.manager.PackageStatusFragmentArgs
import kotlinx.android.synthetic.main.fragment_package_status.*


class PackageStatusFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_package_status, container, false)

        val status1: TextView = root.findViewById(R.id.package_status_driver_1step)
        val status2: TextView = root.findViewById(R.id.package_status_driver_2step)
        val status3: TextView = root.findViewById(R.id.package_status_driver_3step)

        val args: PackageStatusFragmentArgs by navArgs()

        when(args.packageStatus) {
            //Pending
            1 -> {

            }
            //Accepted
            2 -> {
                status1.text = "Accepted"
                status1.setBackgroundResource(R.color.grey_very_light)
            }
            //On the way to vendor
            3 -> {
                status1.text = "Accepted"
                status1.setBackgroundResource(R.color.grey_very_light)
                status2.text = "Dispatched"
                status2.setBackgroundResource(R.color.grey_very_light)
            }
            //On the way to building
            4 -> {
                status1.text = "Accepted"
                status1.setBackgroundResource(R.color.grey_very_light)
                status2.text = "Dispatched"
                status2.setBackgroundResource(R.color.grey_very_light)
                status3.text = "Delivered"
                status3.setBackgroundResource(R.color.grey_very_light)
            }
        }
        return root
    }

}