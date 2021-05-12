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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.PackageListForDriverAdapter
import com.example.package_delivery_app_group_a.adapter.PackageOnWayListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Package
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

        getPackageDriverListFromFirestore()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriverHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }
    fun successPackageDriverListFromFireStore(packageList: ArrayList<Package>){
//        hideShowProgBar()

        if(packageList.size>0) {
            package_list_driver.visibility = View.VISIBLE
//            text_no_driver_found.visibility = View.GONE

            package_list_driver.layoutManager = LinearLayoutManager(activity)
            package_list_driver.setHasFixedSize(true)

            val adapterPackages=
                PackageListForDriverAdapter(requireActivity(), packageList)
            package_list_driver.adapter = adapterPackages

        }
        else{
            package_list_driver.visibility = View.GONE
//            text_no_driver_found.visibility = View.VISIBLE
        }
    }

    private fun getPackageDriverListFromFirestore(){
        //        showProgBar()
        FirestoreClass().getPackageOnWayList(this)
    }

}