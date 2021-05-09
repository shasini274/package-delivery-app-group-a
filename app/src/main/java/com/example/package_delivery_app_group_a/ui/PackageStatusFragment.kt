package com.example.package_delivery_app_group_a.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import kotlinx.android.synthetic.main.fragment_package_status.*


class PackageStatusFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_package_status, container, false)
/*
       val args: PackageStatusFragmentArgs by navArgs()
        val tvTemp: TextView = root.findViewById(R.id.package_detail_number)
        tvTemp.text = args.packageID.toString()
*/

        val textId: TextView = root.findViewById(R.id.package_detail_number)
        textId.text = FirestoreClass().getCurrentUserID()

        val checkboxAccept: CheckBox = root.findViewById(R.id.check_status_pending)
        val checkboxDispatch: CheckBox = root.findViewById(R.id.check_status_dispatched)
        val checkboxDeliver: CheckBox = root.findViewById(R.id.check_status_delivered)

        checkboxAccept.isClickable = false
        checkboxDispatch.isClickable = false
        checkboxDeliver.isClickable = false
        checkboxAccept.setOnClickListener {
            checkboxAccept.isClickable = false
            checkboxDispatch.isClickable = true
            checkboxDeliver.isClickable = false
        }
        checkboxDispatch.setOnClickListener {
            checkboxAccept.isClickable = false
            checkboxDispatch.isClickable = false
            checkboxDeliver.isClickable = true
        }
        checkboxDeliver.setOnClickListener {
            checkboxAccept.isClickable = false
            checkboxDispatch.isClickable = false
            checkboxDeliver.isClickable = false
        }

        return root
    }
}