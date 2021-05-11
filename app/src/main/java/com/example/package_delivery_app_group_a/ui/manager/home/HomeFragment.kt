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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.PackageOnWayListAdapter
import com.example.package_delivery_app_group_a.adapter.PackagePendingListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Package
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_driver.*
import kotlinx.android.synthetic.main.fragment_driver.rv_driver_list_items
import kotlinx.android.synthetic.main.fragment_home.*

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
        getPackageOnwayListFromFirestore()
        /*
        val textView: TextView = root.findViewById(R.id.home_on_the_way)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */

        // get package data and show in listView, need to get data from database later using
//        val vendorArray = resources.getStringArray(R.array.package_ontheway_vendor_array)
//        val buildingArray = resources.getStringArray(R.array.package_ontheway_building_array)
//        val driverArray = resources.getStringArray(R.array.package_ontheway_driver_array)
//        val vendorArray1 = resources.getStringArray(R.array.package_pending_vendor_array)
//        val buildingArray1 = resources.getStringArray(R.array.package_pending_building_array)
//        val driverArray1 = resources.getStringArray(R.array.package_pending_driver_array)
//        val listview1 = root.findViewById<ListView>(R.id.package_listView)
//        val packageListOntheway = ArrayList<HashMap<String, String>>()
//        val packageListPending = ArrayList<HashMap<String, String>>()
//
//        //val arrayAdapter1 = this.context?.let { ArrayAdapter<String>(it, android.R.layout.simple_expandable_list_item_1, vendorArray) } // single string
//        for (i in vendorArray.indices) {
//            val map = HashMap<String, String>()
//
//            // data entry in HashMap
//            map["vendor"] = vendorArray[i]
//            map["building"] = buildingArray[i]
//            map["driver"] = driverArray[i]
//
//            // add the HashMap to ArrayList
//            packageListOntheway.add(map)
//        }
//        for (i in vendorArray1.indices) {
//            val map1 = HashMap<String, String>()
//
//            // data entry in HashMap
//            map1["vendor1"] = vendorArray1[i]
//            map1["building1"] = buildingArray1[i]
//            map1["driver1"] = driverArray1[i]
//
//            // add the HashMap to ArrayList
//            packageListPending.add(map1)
//        }
//
//        //fourth parameter of SimpleAdapter
//        val from = arrayOf("vendor", "building", "driver")
//        //fifth parameter of SimpleAdapter
//        val to = intArrayOf(R.id.home_ontheway_vendor_text, R.id.home_ontheway_building_text, R.id.home_ontheway_driver_text)
//        //fourth parameter of SimpleAdapter
//        val from1 = arrayOf("vendor1", "building1", "driver1")
//        //fifth parameter of SimpleAdapter
//        val to1 = intArrayOf(R.id.home_pending_vendor_text, R.id.home_pending_building_text, R.id.home_pending_driver_text)
//
//        // default: On the way
//        val simpleAdapter = this.context?.let { SimpleAdapter (it, packageListOntheway, R.layout.package_list_ontheway, from, to) }
//
//        listview1.adapter = simpleAdapter
//        listview1.setOnItemClickListener {
//                adapterView, view, position, id ->
//            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageStatusFragment())
//        }



        // Floating Action Button
        val itemType: String = "New Package"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)

        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToNewPackageFragment())
        }

        //two button: On the way, pending
        val onthewayBtn: Button = root.findViewById(R.id.home_ontheway_btn)
        val pendingBtn: Button = root.findViewById(R.id.home_pending_btn)
        onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
        onthewayBtn?.setOnClickListener {
            onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
            pendingBtn.setTextColor(Color.BLACK)
            getPackageOnwayListFromFirestore()



//            val simpleAdapter = this.context?.let { SimpleAdapter (it, packageListOntheway, R.layout.package_list_ontheway, from, to) }
//
//            listview1.adapter = simpleAdapter
//            listview1.setOnItemClickListener {
//                    adapterView, view, position, id ->
//                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageStatusFragment())
//            }

        }
        pendingBtn?.setOnClickListener {
            pendingBtn.setTextColor(Color.parseColor("#00796B"))   //teal_dark
            onthewayBtn.setTextColor(Color.BLACK)
            getPackagePendingListFromFirestore()

//            val simpleAdapter1 = this.context?.let { SimpleAdapter (it, packageListPending, R.layout.package_list_pending, from1, to1) }
//
//            listview1.adapter = simpleAdapter1
//            listview1.setOnItemClickListener {
//                    adapterView, view, position, id ->
//                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageStatusFragment())
//            }

        }


        return root
    }
//    private fun setBtnListener() {}

    fun successPackagePendingListFromFireStore(packageList: ArrayList<Package>){
//        hideShowProgBar()

        if(packageList.size>0) {
            package_listView.visibility = View.VISIBLE
//            text_no_driver_found.visibility = View.GONE

            package_listView.layoutManager = LinearLayoutManager(activity)
            package_listView.setHasFixedSize(true)

            val adapterPackages=
                PackagePendingListAdapter(requireActivity(), packageList)
            package_listView.adapter = adapterPackages

//            val adapterPackages =
//                PackageOnWayListAdapter(requireActivity(), packageList)
//            rv_driver_list_items.adapter = adapterPackages
        }
        else{
            package_listView.visibility = View.GONE
//            text_no_driver_found.visibility = View.VISIBLE
        }
    }
    fun successPackageOnwayListFromFireStore(packageList: ArrayList<Package>){
//        hideShowProgBar()

        if(packageList.size>0) {
            package_listView.visibility = View.VISIBLE
//            text_no_driver_found.visibility = View.GONE

            package_listView.layoutManager = LinearLayoutManager(activity)
            package_listView.setHasFixedSize(true)

            val adapterPackages=
                PackageOnWayListAdapter(requireActivity(), packageList)
            package_listView.adapter = adapterPackages

        }
        else{
            package_listView.visibility = View.GONE
//            text_no_driver_found.visibility = View.VISIBLE
        }
    }

    private fun getPackagePendingListFromFirestore(){
        //        showProgBar()
        FirestoreClass().getPackagePendingList(this)
    }
    private fun getPackageOnwayListFromFirestore(){
        //        showProgBar()
        FirestoreClass().getPackageOnWayList(this)
    }
}