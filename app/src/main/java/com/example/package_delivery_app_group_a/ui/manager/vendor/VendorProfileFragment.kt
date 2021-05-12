package com.example.package_delivery_app_group_a.ui.manager.vendor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingProfileFragmentArgs


class VendorProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profile_vendor, container, false)

        val tvName = root.findViewById<TextView>(R.id.vendor_profile_name)
        val tvAddr = root.findViewById<TextView>(R.id.vendor_profile_address)
        val tvEmail = root.findViewById<TextView>(R.id.vendor_profile_email)
        val tvPerson = root.findViewById<TextView>(R.id.vendor_profile_person)
        val tvNumber = root.findViewById<TextView>(R.id.vendor_profile_number)
        val tvId = root.findViewById<TextView>(R.id.vendor_profile_id)

        val args: VendorProfileFragmentArgs by navArgs()

        tvName.text = args.vendorProName
        tvAddr.text = args.vendorProAddr
        tvEmail.text = args.vendorProEmail
        tvPerson.text = args.vendorProPerson
        tvNumber.text = args.vendorProNum.toString()
        tvId.text = args.vendorProId


        return root
    }


}