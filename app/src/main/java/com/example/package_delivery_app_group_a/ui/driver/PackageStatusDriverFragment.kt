package com.example.package_delivery_app_group_a.ui.driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass


class PackageStatusDriverFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_package_status_driver, container, false)
        val packageStatus = 3  // 1,2,3,4

/*
        val textId: TextView = root.findViewById(R.id.package_detail_number_driver)
        textId.text = FirestoreClass().getCurrentUserID()
*/

        val checkboxAccept: CheckBox = root.findViewById(R.id.check_status_pending_driver)
        val checkboxDispatch: CheckBox = root.findViewById(R.id.check_status_dispatched_driver)
        val checkboxDeliver: CheckBox = root.findViewById(R.id.check_status_delivered_driver)
        val acceptedTV: TextView = root.findViewById(R.id.package_status_accepted)
        val dispatchedTV: TextView = root.findViewById(R.id.package_status_dispatched)
        val deliveredTV: TextView = root.findViewById(R.id.package_status_delivered)

        when(packageStatus) {
            1 -> {
                checkboxAccept.isClickable = true
                checkboxDispatch.isClickable = false
                checkboxDeliver.isClickable = false
                checkboxAccept.setOnClickListener {
                    checkboxAccept.visibility = View.GONE
                    acceptedTV.visibility = View.VISIBLE
                    //changePackageStatus(2)
                }
            }
            2 -> {
                checkboxDispatch.isClickable = true
                checkboxDeliver.isClickable = false
                checkboxAccept.visibility = View.GONE
                acceptedTV.visibility = View.VISIBLE
                checkboxDispatch.setOnClickListener {
                    checkboxDispatch.visibility = View.GONE
                    dispatchedTV.visibility = View.VISIBLE
                    //changePackageStatus(3)
                }
            }
            3 -> {
                checkboxDeliver.isClickable = true
                checkboxAccept.visibility = View.GONE
                acceptedTV.visibility = View.VISIBLE
                checkboxDispatch.visibility = View.GONE
                dispatchedTV.visibility = View.VISIBLE
                checkboxDeliver.setOnClickListener {
                    checkboxDeliver.visibility = View.GONE
                    deliveredTV.visibility = View.VISIBLE
                    //changePackageStatus(4)
                }
            }
            4 -> {
                checkboxAccept.visibility = View.GONE
                acceptedTV.visibility = View.VISIBLE
                checkboxDispatch.visibility = View.GONE
                dispatchedTV.visibility = View.VISIBLE
                checkboxDeliver.visibility = View.GONE
                deliveredTV.visibility = View.VISIBLE
            }
        }

        return root
    }


}