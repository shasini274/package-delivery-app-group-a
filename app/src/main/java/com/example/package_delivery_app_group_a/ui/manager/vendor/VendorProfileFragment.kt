package com.example.package_delivery_app_group_a.ui.manager.vendor

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
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingProfileFragmentArgs
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingProfileFragmentDirections
import com.example.package_delivery_app_group_a.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile_vendor.*


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

        val editName = root.findViewById<TextView>(R.id.vendor_profile_name_edit)
        val editAddr = root.findViewById<TextView>(R.id.vendor_profile_address_edit)
        val editEmail = root.findViewById<TextView>(R.id.vendor_profile_email_edit)
        val editPerson = root.findViewById<TextView>(R.id.vendor_profile_person_edit)
        val editNumber = root.findViewById<TextView>(R.id.vendor_profile_number_edit)

        val btn_edit = root.findViewById<Button>(R.id.btn_vendor_profile_edit)
        val btn_update = root.findViewById<Button>(R.id.btn_vendor_profile_update)

        val args: VendorProfileFragmentArgs by navArgs()

        tvName.text = args.vendorProName
        tvAddr.text = args.vendorProAddr
        tvEmail.text = args.vendorProEmail
        tvPerson.text = args.vendorProPerson
        tvNumber.text = args.vendorProNum.toString()

        editName.hint = args.vendorProName
        editAddr.hint = args.vendorProAddr
        editEmail.hint = args.vendorProEmail
        editPerson.hint = args.vendorProPerson
        editNumber.hint = args.vendorProNum.toString()

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
            updateOneVendorProfile(args.vendorProId)
            Navigation.findNavController(view).navigate(
                VendorProfileFragmentDirections.actionVendorProfileFragmentToNavVendor()
            )
        }
        return root
    }

    fun updateOneVendorProfile(vendorId: String){
        //    val driverEmail = driver_profile_id.text.trim().toString()
        val name = vendor_profile_name_edit.text.trim().toString()
        val addr = vendor_profile_address_edit.text.trim().toString()
        val email = vendor_profile_email_edit.text.trim().toString()
        val person = vendor_profile_person_edit.text.trim().toString()
        val number = vendor_profile_number_edit.text.trim().toString()
        val userHashMap = HashMap<String, Any>()
        //number
        if(name.isNotEmpty()){
            userHashMap[Constants.VENDOR_NAME] = name
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
        FirestoreClass().editOneVendorProfile(this, userHashMap, vendorId)
    }

}