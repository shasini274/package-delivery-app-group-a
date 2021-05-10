package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

//       val args: PackageStatusFragmentArgs by navArgs()

        return root
    }

}