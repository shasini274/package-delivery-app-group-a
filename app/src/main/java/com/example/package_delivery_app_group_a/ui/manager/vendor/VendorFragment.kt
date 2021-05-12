package com.example.package_delivery_app_group_a.ui.manager.vendor

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.VendorListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Vendor
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_vendor.*

class VendorFragment : Fragment() {

    private lateinit var vendorViewModel: VendorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vendorViewModel =
            ViewModelProvider(this).get(VendorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vendor, container, false)
/*        val textView: TextView = root.findViewById(R.id.text_vendor)
        vendorViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        val itemType: String = "New Vendor"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)
        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(VendorFragmentDirections.actionNavVendorToAddNewVendorFragment())
        }

        return root
    }
    override fun onResume(){
        super.onResume()
        getVendorListFromFirestore()
    }

    fun successVendorListFromFireStore(vendorList: ArrayList<Vendor>){
//        hideShowProgBar()

        if(vendorList.size>0) {
            rv_vendor_list_items.visibility = View.VISIBLE
            text_no_vendor_found.visibility = View.GONE

            rv_vendor_list_items.layoutManager = LinearLayoutManager(activity)
            rv_vendor_list_items.setHasFixedSize(true)

            val adapterDrivers =
                VendorListAdapter(requireActivity(), vendorList)
            rv_vendor_list_items.adapter = adapterDrivers
        }
        else{
            rv_vendor_list_items.visibility = View.GONE
            text_no_vendor_found.visibility = View.VISIBLE
        }
    }
    private fun getVendorListFromFirestore(){
//        showProgBar()
        FirestoreClass().getVendorList(this)
    }

}