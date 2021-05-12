package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.R
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivityViewModel

class NewPackageFragment : Fragment() {

    //private lateinit var newPackageViewModel: NewPackageViewModel
    private val mainViewModel:ManagerMainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = NewPackageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*
        newPackageViewModel =
            ViewModelProvider(this).get(NewPackageViewModel::class.java)
*/

        val root = inflater.inflate(R.layout.new_package_fragment, container, false)

        val args: NewPackageFragmentArgs by navArgs()
        val tvDriverList = root.findViewById<TextView>(R.id.new_package_driver)
        val tvVendorList = root.findViewById<TextView>(R.id.new_package_vendor)
        val tvBuildingList = root.findViewById<TextView>(R.id.new_package_building)

        when(args.newPackageParamType) {
            //Vendor
            1 -> {
                mainViewModel.selectVendorId.value = args.newPackageParamId
            }
            //Driver
            2 -> {
                mainViewModel.selectDriverId.value = args.newPackageParamId
            }
            //Building
            3 -> {

            }
            //New package
            100 -> {
                mainViewModel.selectVendorId.value = ""
                mainViewModel.selectDriverId.value = ""
            }
        }

        mainViewModel.selectDriverId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvDriverList.text = it
            }
            else {
                tvDriverList.text = "Select Driver"
            }
        })
        mainViewModel.selectVendorId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvVendorList.text = it
            }
            else {
                tvVendorList.text = "Select Vendor"
            }
        })

        tvDriverList?.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(NewPackageFragmentDirections.actionNewPackageFragmentToNewPackDriverListFragment())
        }
        tvVendorList?.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(NewPackageFragmentDirections.actionNewPackageFragmentToNewPackVendorListFragment())
        }

        return root
    }

/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newPackViewModel = ViewModelProvider(this).get(NewPackageTestViewModel::class.java)
        // TODO: Use the ViewModel
    }
*/

}