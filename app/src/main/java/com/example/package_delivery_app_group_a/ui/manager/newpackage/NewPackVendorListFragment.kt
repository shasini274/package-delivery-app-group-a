package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.VendorListInNewPackAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Vendor
import kotlinx.android.synthetic.main.new_pack_vendor_list_fragment.*

class NewPackVendorListFragment : Fragment() {

    companion object {
        fun newInstance() = NewPackVendorListFragment()
    }

    private lateinit var viewModel: NewPackVendorListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_pack_vendor_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewPackVendorListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume(){
        super.onResume()
        getVendorListFromFirestore()
    }

    fun successVendorListFromFireStore(vendorList: ArrayList<Vendor>){
//        hideShowProgBar()

        if(vendorList.size>0) {
            rv_new_package_vendor_list_items.visibility = View.VISIBLE
            text_new_package_no_vendor_found.visibility = View.GONE

            rv_new_package_vendor_list_items.layoutManager = LinearLayoutManager(activity)
            rv_new_package_vendor_list_items.setHasFixedSize(true)

            val adapterDrivers =
                VendorListInNewPackAdapter(requireActivity(), vendorList)
            rv_new_package_vendor_list_items.adapter = adapterDrivers
        }
        else{
            rv_new_package_vendor_list_items.visibility = View.GONE
            text_new_package_no_vendor_found.visibility = View.VISIBLE
        }
    }

    private fun getVendorListFromFirestore(){
//        showProgBar()
        FirestoreClass().getVendorList(this)
    }

}