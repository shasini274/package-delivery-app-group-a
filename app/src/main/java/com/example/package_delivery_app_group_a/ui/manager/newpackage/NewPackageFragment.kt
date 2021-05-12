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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.adapter.DriverListAdapter
import com.example.package_delivery_app_group_a.adapter.ItemListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Item
import com.example.package_delivery_app_group_a.models.Package
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivityViewModel
import kotlinx.android.synthetic.main.new_package_fragment.*

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
        val tvVendorList = root.findViewById<TextView>(R.id.new_package_vendor_select)
        val tvDriverList = root.findViewById<TextView>(R.id.new_package_driver_select)
        val tvBuildingList = root.findViewById<TextView>(R.id.new_package_building_select)
        val addPackageBtn = root.findViewById<Button>(R.id.new_package_btn)

        when(args.newPackageParamType) {
            //Vendor
            1 -> {
                mainViewModel.selectVendorId.value = args.newPackageParamId
                mainViewModel.selectVendorName.value = args.newPackageParamName
            }
            //Driver
            2 -> {
                mainViewModel.selectDriverId.value = args.newPackageParamId
                mainViewModel.selectDriverName.value = args.newPackageParamName
            }
            //Building
            3 -> {
                mainViewModel.selectBuildingId.value = args.newPackageParamId
                mainViewModel.selectBuildingName.value = args.newPackageParamName
            }
            //New package
            100 -> {
                mainViewModel.selectVendorId.value = ""
                mainViewModel.selectDriverId.value = ""
                mainViewModel.selectBuildingId.value = ""
                mainViewModel.selectVendorName.value = ""
                mainViewModel.selectDriverName.value = ""
                mainViewModel.selectBuildingName.value = ""
            }
        }

        mainViewModel.selectVendorName.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvVendorList.text = it
            }
            else {
                tvVendorList.text = "Select Vendor"
            }
        })
        mainViewModel.selectDriverName.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                tvDriverList.text = it
            }
            else {
                tvDriverList.text = "Select Driver"
            }
        })
        mainViewModel.selectBuildingName.observe(viewLifecycleOwner, Observer {
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

        val itemHashMap = HashMap<String, Any>()
        itemHashMap["Sand"] = 4


        addPackageBtn.setOnClickListener{
            addPackage(
                mainViewModel.selectVendorId.value.toString(),
                mainViewModel.selectVendorName.value.toString(),
                mainViewModel.selectDriverId.value.toString(),
                mainViewModel.selectDriverName.value.toString(),
                mainViewModel.selectBuildingId.value.toString(),
                mainViewModel.selectBuildingName.value.toString(),
                itemHashMap
            )
        }


        return root
    }
    private fun addPackage (
        vendorId: String, vendorName: String,
        driverId: String, driverName: String,
        buildingId: String, buildingName: String,
        item: HashMap<String, Any>
    ) {
        if (checkLayoutInputs(vendorId, driverId, buildingId)) {
            showProgBar()
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();

            val packageInfo = Package(
                vendorId,
                vendorName,
                driverId,
                driverName,
                buildingId,
                buildingName,
                0,
                item)
            FirestoreClass().addPackage(this, packageInfo)
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
    fun packageRegistrationSuccess(){
        // Hide the progress dialog
        hideShowProgBar()
//        showErrorSnackBar(resources.getString(R.string.not_err_details), false)
//        FirebaseAuth.getInstance().signOut()
//        finish()
    }

    override fun onResume(){
        super.onResume()
        getItemListFromFirestore()
    }

    fun successItemListFromFireStore(itemList: ArrayList<Item>){
//        hideShowProgBar()

        if(itemList.size>0) {
            rv_new_package_item_list.visibility = View.VISIBLE

            rv_new_package_item_list.layoutManager = LinearLayoutManager(activity)
            rv_new_package_item_list.setHasFixedSize(true)

            val adapterItem =
                ItemListAdapter(requireActivity(), itemList)
            rv_new_package_item_list.adapter = adapterItem
        }
        else{
            rv_new_package_item_list.visibility = View.GONE
        }
    }
    private fun getItemListFromFirestore(){
//        showProgBar()
        FirestoreClass().getItemList(this)
    }


}