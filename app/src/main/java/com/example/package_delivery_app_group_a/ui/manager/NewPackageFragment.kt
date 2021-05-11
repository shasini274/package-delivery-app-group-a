package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.VendorListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Vendor
import com.example.package_delivery_app_group_a.utils.Constants
import kotlinx.android.synthetic.main.fragment_new_package.*
import kotlinx.android.synthetic.main.fragment_package_detail.*


class NewPackageFragment : Fragment() {

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val root = inflater.inflate(R.layout.fragment_new_package, container, false)
//        getVendorListFromFirestore()
////        getVendorListFromFirestore()
///*        val args: NewPackageFragmentArgs by navArgs()
//
//        val tvTemp: TextView = root.findViewById(R.id.new_item_test_text2)
//        tvTemp.text = args.newItemType
//
//        (activity as AppCompatActivity).supportActionBar?.title = args.newItemType
//        */
//
//            return root
//    }
//

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//
//    fun successVendorListFromFireStore(vendorList: ArrayList<Vendor>) {
//        if (spinner != null) {
//            print("HELLLOOOOOO")
//            if (vendorList.size > 0) {
//                val adapter = VendorListAdapter(requireActivity(), vendorList)
//                spinner.adapter = adapter
//
//                vendor_type.onItemSelectedListener = object :
//                    AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(
//                        parent: AdapterView<*>,
//                        view: View, position: Int, id: Long
//                    ) {
//                        val selectedItem = parent.getItemAtPosition(position)
//                        println("******")
//                        println(selectedItem)
//                    }
//
//                    override fun onNothingSelected(parent: AdapterView<*>) {
//                        // write code to perform some action
//                    }
//                }
//            }
//        }
//
//    }
//        val spinner = vendor_type

//        if (vendor_type != null){
//            val adapterVendors =
//                VendorListAdapter(requireActivity(), vendorList)
//            spinner.adapter = adapterVendors
//        if (vendorList.size>0){
//            val adapterVendors = ArrayAdapter(requireActivity(),
//            android.R.layout.simple_spinner_item, vendorList)
//            vendor_type.adapter = adapterVendors
//
//            vendor_type.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    println(vendorList[position])
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }


////            val adapterVendors = ArrayAdapter(requireActivity(),
////            android.R.layout.simple_spinner_item, vendorList)
////
////            vendor_type.adapter = adapterVendors
//
//            val adapterVendors =
//                VendorListAdapter(requireActivity(), vendorList)
//
//            vendor_type.adapter = adapterVendors

//            }
//        }

//    }
//    private fun getVendorListFromFirestore(){
////        showProgBar()
//        FirestoreClass().getVendorList(this)
//    }


}