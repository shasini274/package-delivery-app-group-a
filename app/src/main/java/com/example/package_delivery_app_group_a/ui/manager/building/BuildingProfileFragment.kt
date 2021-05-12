package com.example.package_delivery_app_group_a.ui.manager.building

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R


class BuildingProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profile_building, container, false)

        val tvName = root.findViewById<TextView>(R.id.building_profile_id)
        val tvAddr = root.findViewById<TextView>(R.id.building_profile_address)
        val tvEmail = root.findViewById<TextView>(R.id.building_profile_email)
        val tvPerson = root.findViewById<TextView>(R.id.building_profile_person)
        val tvNumber = root.findViewById<TextView>(R.id.building_profile_number)
        val tvId = root.findViewById<TextView>(R.id.building_profile_id)

        val args: BuildingProfileFragmentArgs by navArgs()

        tvName.text = args.buildingProName
        tvAddr.text = args.buildingProAddr
        tvEmail.text = args.buildingProEmail
        tvPerson.text = args.buildingProPerson
        tvNumber.text = args.buildingProNum.toString()
        tvId.text = args.buildingProId


        return root
    }


}