package com.example.package_delivery_app_group_a.ui.manager.driver

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
import com.example.package_delivery_app_group_a.ui.manager.home.HomeFragmentDirections
import com.example.package_delivery_app_group_a.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile_driver.*


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
        val editFName = root.findViewById<TextView>(R.id.driver_profile_fname_edit)
        val editLName = root.findViewById<TextView>(R.id.driver_profile_lname_edit)
        val editNumber = root.findViewById<TextView>(R.id.driver_profile_mobile_edit)
        val tvId = root.findViewById<TextView>(R.id.driver_profile_id)
        val btn_edit = root.findViewById<Button>(R.id.btn_driver_profile_edit)
        val btn_update = root.findViewById<Button>(R.id.btn_driver_profile_update)

        val args: DriverProfileFragmentArgs by navArgs()

        tvFName.text = args.driverProFName
        tvLName.text = args.driverProLName
        tvNumber.text = args.driverProMobile.toString()
        tvId.text = args.driverProId

        editFName.hint = args.driverProFName
        editLName.hint = args.driverProLName
        editNumber.hint = args.driverProMobile.toString()

        btn_edit.setOnClickListener{
            btn_edit.isVisible = false
            btn_update.isVisible = true

            tvFName.isVisible = false
            tvLName.isVisible = false
            tvNumber.isVisible = false

            editFName.isVisible = true
            editLName.isVisible = true
            editNumber.isVisible = true
        }
        btn_update.setOnClickListener { view ->
            updateOneDriverProfile(args.driverProId)
            Navigation.findNavController(view).navigate(
                DriverProfileFragmentDirections.actionDriverProfileFragmentToNavDriver()
            )
        }

        return root
    }

    fun updateOneDriverProfile(driverEmail: String){
    //    val driverEmail = driver_profile_id.text.trim().toString()
        val fname = driver_profile_fname_edit.text.trim().toString()
        val lname = driver_profile_lname_edit.text.trim().toString()
        val number = driver_profile_mobile_edit.text.trim().toString()
        val userHashMap = HashMap<String, Any>()
        //number
        if(fname.isNotEmpty()){
            userHashMap[Constants.FNAME] = fname
        }
        if(lname.isNotEmpty()){
            userHashMap[Constants.LNAME] = lname
        }
        if(number.isNotEmpty()){
            userHashMap[Constants.MOBILE] = number.toLong()
        }

        userHashMap[Constants.COMPLETED_PROFILE] = 1
        FirestoreClass().editOneDriverProfile(this, userHashMap, driverEmail)
    }


    fun showPicture() {

    }
}