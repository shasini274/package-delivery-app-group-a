package com.example.package_delivery_app_group_a.ui.driver.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.driver.DriverFragmentDirections
import kotlinx.android.synthetic.main.fragment_home_driver.*

class DriverHomeFragment : Fragment() {

    companion object {
        fun newInstance() = DriverHomeFragment()
    }

    private lateinit var viewModel: DriverHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home_driver, container, false)

        val vendorArrayDriver = resources.getStringArray(R.array.driver_package_vendor_array)
        val buildingArrayDriver = resources.getStringArray(R.array.driver_package_building_array)
        val driverArrayDriver = resources.getStringArray(R.array.driver_package_driver_array)
        val packageListDriver = ArrayList<HashMap<String, String>>()
        val packageList: ListView = root.findViewById(R.id.package_listView_driver)

        for (i in vendorArrayDriver.indices) {
            val map = HashMap<String, String>()
            // data entry in HashMap
            map["vendor"] = vendorArrayDriver[i]
            map["building"] = buildingArrayDriver[i]
            map["driver"] = driverArrayDriver[i]
            // add the HashMap to ArrayList
            packageListDriver.add(map)
        }
        //fourth parameter of SimpleAdapter
        val from = arrayOf("vendor", "building", "driver")
        //fifth parameter of SimpleAdapter
        val to = intArrayOf(R.id.home_ontheway_vendor_text, R.id.home_ontheway_building_text, R.id.home_ontheway_driver_text)
        val simpleAdapter = this.context?.let { SimpleAdapter (it, packageListDriver, R.layout.package_list_ontheway, from, to) }

        packageList.adapter = simpleAdapter

        packageList.setOnItemClickListener {
                adapterView, view, position, id ->
            Navigation.findNavController(view).navigate(DriverHomeFragmentDirections.actionNavHomeDriverToPackageStatusDriverFragment())
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriverHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}