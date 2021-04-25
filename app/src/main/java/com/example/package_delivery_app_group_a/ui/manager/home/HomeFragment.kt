package com.example.package_delivery_app_group_a.ui.manager.home

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.package_delivery_app_group_a.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*
        val textView: TextView = root.findViewById(R.id.home_on_the_way)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */

        // get package data and show in listView, need to get data from database later using
        val vendorArray = resources.getStringArray(R.array.package_ontheway_vendor_array)
        val buildingArray = resources.getStringArray(R.array.package_ontheway_building_array)
        val driverArray = resources.getStringArray(R.array.package_ontheway_driver_array)
        val vendorArray1 = resources.getStringArray(R.array.package_pending_vendor_array)
        val buildingArray1 = resources.getStringArray(R.array.package_pending_building_array)
        val driverArray1 = resources.getStringArray(R.array.package_pending_driver_array)
        val listview1 = root.findViewById<ListView>(R.id.package_listView)
        val packageListOntheway = ArrayList<HashMap<String, String>>()
        val packageListPending = ArrayList<HashMap<String, String>>()

        //val arrayAdapter1 = this.context?.let { ArrayAdapter<String>(it, android.R.layout.simple_expandable_list_item_1, vendorArray) } // single string
        for (i in vendorArray.indices) {
            val map = HashMap<String, String>()

            // data entry in HashMap
            map["vendor"] = vendorArray[i]
            map["building"] = buildingArray[i]
            map["driver"] = driverArray[i]

            // add the HashMap to ArrayList
            packageListOntheway.add(map)
        }
        for (i in vendorArray1.indices) {
            val map1 = HashMap<String, String>()

            // data entry in HashMap
            map1["vendor1"] = vendorArray1[i]
            map1["building1"] = buildingArray1[i]
            map1["driver1"] = driverArray1[i]

            // add the HashMap to ArrayList
            packageListPending.add(map1)
        }

        //fourth parameter of SimpleAdapter
        val from = arrayOf("vendor", "building", "driver")
        //fifth parameter of SimpleAdapter
        val to = intArrayOf(R.id.home_ontheway_vendor_text, R.id.home_ontheway_building_text, R.id.home_ontheway_driver_text)
        //fourth parameter of SimpleAdapter
        val from1 = arrayOf("vendor1", "building1", "driver1")
        //fifth parameter of SimpleAdapter
        val to1 = intArrayOf(R.id.home_pending_vendor_text, R.id.home_pending_building_text, R.id.home_pending_driver_text)

        // default: On the way
        val simpleAdapter = this.context?.let { SimpleAdapter (it, packageListOntheway, R.layout.package_list_ontheway, from, to) }

        listview1.adapter = simpleAdapter
        listview1.setOnItemClickListener {
                adapterView, view, position, id ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageDetailFragment(position))
        }


        // Floating Action Button
        val itemType: String = "New Package"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)

        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToNewItemFragment(itemType))
        }

        //two button: On the way, pending
        val onthewayBtn: Button = root.findViewById(R.id.home_ontheway_btn)
        val pendingBtn: Button = root.findViewById(R.id.home_pending_btn)
        onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
        onthewayBtn?.setOnClickListener {
            onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
            pendingBtn.setTextColor(Color.BLACK)

            val simpleAdapter = this.context?.let { SimpleAdapter (it, packageListOntheway, R.layout.package_list_ontheway, from, to) }

            listview1.adapter = simpleAdapter
            listview1.setOnItemClickListener {
                    adapterView, view, position, id ->
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageDetailFragment(position))
            }

        }
        pendingBtn?.setOnClickListener {
            pendingBtn.setTextColor(Color.parseColor("#00796B"))   //teal_dark
            onthewayBtn.setTextColor(Color.BLACK)

            val simpleAdapter1 = this.context?.let { SimpleAdapter (it, packageListPending, R.layout.package_list_pending, from1, to1) }

            listview1.adapter = simpleAdapter1
            listview1.setOnItemClickListener {
                    adapterView, view, position, id ->
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageDetailFragment(position))
            }

        }


        return root
    }
//    private fun setBtnListener() {}

}