package com.example.package_delivery_app_group_a.ui.manager.driver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.DriverListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Driver
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_driver.*

class DriverFragment : BaseFragment() {
    private lateinit var driverViewModel: DriverViewModel
    private lateinit var viewModel: DriverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        driverViewModel =
            ViewModelProvider(this).get(DriverViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_driver, container, false)

        val itemType: String = "New Driver"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)
        fab.setOnClickListener{ view ->
            Navigation.findNavController(view).navigate(DriverFragmentDirections.actionNavDriverToAddNewDriverFragment())
        }
        return root
    }

    override fun onResume(){
        super.onResume()
        getDriverListFromFirestore()
    }

    fun successDriverListFromFireStore(driverList: ArrayList<Driver>){
//        hideShowProgBar()

        if(driverList.size>0) {
            rv_driver_list_items.visibility = View.VISIBLE
            text_no_driver_found.visibility = View.GONE

            rv_driver_list_items.layoutManager = LinearLayoutManager(activity)
            rv_driver_list_items.setHasFixedSize(true)

            val adapterDrivers =
                DriverListAdapter(requireActivity(), driverList)
            rv_driver_list_items.adapter = adapterDrivers
        }
        else{
            rv_driver_list_items.visibility = View.GONE
            text_no_driver_found.visibility = View.VISIBLE
        }
    }
    private fun getDriverListFromFirestore(){
//        showProgBar()
        FirestoreClass().getDriverList(this)
    }

}