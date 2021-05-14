package com.example.package_delivery_app_group_a.ui.manager.building

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.ui.manager.driver.DriverProfileFragmentDirections
import com.example.package_delivery_app_group_a.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile_building.*



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

        val tvName = root.findViewById<TextView>(R.id.building_profile_name)
        val tvAddr = root.findViewById<TextView>(R.id.building_profile_address)
        val tvEmail = root.findViewById<TextView>(R.id.building_profile_email)
        val tvPerson = root.findViewById<TextView>(R.id.building_profile_person)
        val tvNumber = root.findViewById<TextView>(R.id.building_profile_number)

        val editName = root.findViewById<TextView>(R.id.building_profile_name_edit)
        val editAddr = root.findViewById<TextView>(R.id.building_profile_address_edit)
        val editEmail = root.findViewById<TextView>(R.id.building_profile_email_edit)
        val editPerson = root.findViewById<TextView>(R.id.building_profile_person_edit)
        val editNumber = root.findViewById<TextView>(R.id.building_profile_number_edit)

        val btn_edit = root.findViewById<Button>(R.id.btn_building_profile_edit)
        val btn_update = root.findViewById<Button>(R.id.btn_building_profile_update)

        val args: BuildingProfileFragmentArgs by navArgs()

        tvName.text = args.buildingProName
        tvAddr.text = args.buildingProAddr
        tvEmail.text = args.buildingProEmail
        tvPerson.text = args.buildingProPerson
        tvNumber.text = args.buildingProNum.toString()

        editName.hint = args.buildingProName
        editAddr.hint = args.buildingProAddr
        editEmail.hint = args.buildingProEmail
        editPerson.hint = args.buildingProPerson
        editNumber.hint = args.buildingProNum.toString()

        btn_edit.setOnClickListener{
            btn_edit.isVisible = false
            btn_update.isVisible = true

            tvName.isVisible = false
            tvAddr.isVisible = false
            tvEmail.isVisible = false
            tvPerson.isVisible = false
            tvNumber.isVisible = false


            editName.isVisible = true
            editAddr.isVisible = true
            editEmail.isVisible = true
            editPerson.isVisible = true
            editNumber.isVisible = true
        }
        btn_update.setOnClickListener { view ->
            updateOneBuildingProfile(args.buildingProId)
            Navigation.findNavController(view).navigate(
                BuildingProfileFragmentDirections.actionBuildingProfileFragmentToNavBuilding()
            )
        }
            return root
    }

    fun updateOneBuildingProfile(buildingId: String){
        //    val driverEmail = driver_profile_id.text.trim().toString()
        val name = building_profile_name_edit.text.trim().toString()
        val addr = building_profile_address_edit.text.trim().toString()
        val email = building_profile_email_edit.text.trim().toString()
        val person = building_profile_person_edit.text.trim().toString()
        val number = building_profile_number_edit.text.trim().toString()
        val userHashMap = HashMap<String, Any>()
        //number
        if(name.isNotEmpty()){
            userHashMap[Constants.SITE_NAME] = name
        }
        if(addr.isNotEmpty()){
            userHashMap[Constants.ADDRESS] = addr
        }
        if(email.isNotEmpty()){
            userHashMap[Constants.EMAIL] = email
        }
        if(person.isNotEmpty()){
            userHashMap[Constants.CONTACT_PERSON] = person
        }
        if(number.isNotEmpty()){
            userHashMap[Constants.CONTACT_NUMBER] = number.toLong()
        }

        userHashMap[Constants.COMPLETED_PROFILE] = 1
        FirestoreClass().editOneBuildingProfile(this, userHashMap, buildingId)
    }

}