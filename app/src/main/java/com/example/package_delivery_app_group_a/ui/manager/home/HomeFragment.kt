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

        // Floating Action Button
        val itemType: Int = 100
        val itemId: String = ""
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)

        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToNewPackageFragment(itemType,itemId))
        }

        //two button: On the way, pending
        val onthewayBtn: Button = root.findViewById(R.id.home_ontheway_btn)
        val pendingBtn: Button = root.findViewById(R.id.home_pending_btn)
        onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
        onthewayBtn?.setOnClickListener {
            onthewayBtn.setTextColor(Color.parseColor("#00796B"))  //teal_dark
            pendingBtn.setTextColor(Color.BLACK)
            getPackageOnwayListFromFirestore()
        }

        pendingBtn?.setOnClickListener {
            pendingBtn.setTextColor(Color.parseColor("#00796B"))   //teal_dark
            onthewayBtn.setTextColor(Color.BLACK)
            getPackagePendingListFromFirestore()

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

//            package_listView.setOnClickListener{}

            //            listview1.setOnItemClickListener {
//                    adapterView, view, position, id ->
//                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToPackageStatusFragment())
//            }

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