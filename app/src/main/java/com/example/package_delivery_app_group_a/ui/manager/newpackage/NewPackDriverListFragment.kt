package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.DriverListInNewPackAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Driver
import kotlinx.android.synthetic.main.new_pack_driver_list_fragment.*

class NewPackDriverListFragment : Fragment() {

    companion object {
        fun newInstance() = NewPackDriverListFragment()
    }

    private lateinit var viewModel: NewPackDriverListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_pack_driver_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewPackDriverListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume(){
        super.onResume()
        getDriverListFromFirestore()
    }
    fun successDriverListFromFireStore(driverList: ArrayList<Driver>){
//        hideShowProgBar()

        if(driverList.size>0) {
            rv_new_package_driver_list_items.visibility = View.VISIBLE
            text_new_package_no_driver_found.visibility = View.GONE

            rv_new_package_driver_list_items.layoutManager = LinearLayoutManager(activity)
            rv_new_package_driver_list_items.setHasFixedSize(true)

            val adapterDrivers =
                DriverListInNewPackAdapter(requireActivity(), driverList)
            rv_new_package_driver_list_items.adapter = adapterDrivers
        }
        else{
            rv_new_package_driver_list_items.visibility = View.GONE
            text_new_package_no_driver_found.visibility = View.VISIBLE
        }
    }

    private fun getDriverListFromFirestore(){
//        showProgBar()
        FirestoreClass().getDriverList(this)
    }

}