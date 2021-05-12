package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.R
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Package
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivityViewModel

class NewPackageFragment : BaseFragment() {

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
        val tvVendorList = root.findViewById<TextView>(R.id.new_package_vendor)
        val tvDriverList = root.findViewById<TextView>(R.id.new_package_driver)
        val tvBuildingList = root.findViewById<TextView>(R.id.new_package_building)
        val addPackageBtn = root.findViewById<Button>(R.id.new_package_btn)

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
                mainViewModel.selectBuildingId.value = args.newPackageParamId
            }
            //New package
            100 -> {
                mainViewModel.selectVendorId.value = ""
                mainViewModel.selectDriverId.value = ""
                mainViewModel.selectBuildingId.value = ""
            }
        }

        mainViewModel.selectVendorId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvVendorList.text = it
            }
            else {
                tvVendorList.text = "Select Vendor"
            }
        })
        mainViewModel.selectDriverId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvDriverList.text = it
            }
            else {
                tvDriverList.text = "Select Driver"
            }
        })
        mainViewModel.selectBuildingId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvBuildingList.text = it
            }
            else {
                tvBuildingList.text = "Select Building"
            }
        })

        tvDriverList?.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(NewPackageFragmentDirections.actionNewPackageFragmentToNewPackDriverListFragment())
        }
        tvVendorList?.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(NewPackageFragmentDirections.actionNewPackageFragmentToNewPackVendorListFragment())
        }
        tvBuildingList?.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(NewPackageFragmentDirections.actionNewPackageFragmentToNewPackBuildingListFragment())
        }

        addPackageBtn.setOnClickListener{
            addPackage(
                tvVendorList.text.toString(),
                tvDriverList.text.toString(),
                tvBuildingList.text.toString()
            )
        }
        return root
    }
    private fun addPackage (vendorId: String, driverId: String, buildingId: String) {
        if (checkLayoutInputs(vendorId, driverId, buildingId)) {
            //showProgBar()
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
/*
            val package = Package(
                drivEmail,
                drivFname,
                drivLname)
            FirestoreClass().addPackage(this, driver)
*/
        }

    }
    private fun checkLayoutInputs(vendorId: String, driverId: String, buildingId: String): Boolean {
//        hideShowProgBar()
        return when {

            vendorId.isEmpty() && driverId.isEmpty() && buildingId.isEmpty() -> {
                println("Input Required")
//                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }
            driverId.isEmpty() -> {
                println("Please select vendor")
                false
            }
            driverId.isEmpty() -> {
                println("Please select driver")
                false
            }
            buildingId.isEmpty() -> {
                println("Please select building")
                false
            }
            else -> {
                println("Ok")
                //showErrorSnackBar(resources.getString(R.string.not_err_details), false)
                true
            }
        }
    }

/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newPackViewModel = ViewModelProvider(this).get(NewPackageTestViewModel::class.java)
        // TODO: Use the ViewModel
    }
*/

}