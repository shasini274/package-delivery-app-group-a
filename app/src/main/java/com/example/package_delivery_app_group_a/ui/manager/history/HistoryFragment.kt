package com.example.package_delivery_app_group_a.ui.manager.history

import PackageDeliveredListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Package
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        historyViewModel =
//            ViewModelProvider(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)

//        val textView: TextView = root.findViewById(R.id.text_history)
//        historyViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
        override fun onResume() {
        super.onResume()
        getPackageDeliveredDetailsFromFirestore()
    }

    fun getPackageDeliveredDetailsFromFirestore(){
        FirestoreClass().getPackageDeliveredList(this)
    }
    fun successPackageDeliveredListFromFireStore(packageList: ArrayList<Package>){
//        hideShowProgBar()

        if(packageList.size>0) {
            package_deliveredlistView.visibility = View.VISIBLE
//            text_no_driver_found.visibility = View.GONE

            package_deliveredlistView.layoutManager = LinearLayoutManager(activity)
            package_deliveredlistView.setHasFixedSize(true)

            val adapterPackages=
                PackageDeliveredListAdapter(requireActivity(), packageList)
            package_deliveredlistView.adapter = adapterPackages

        }
        else{
            package_deliveredlistView.visibility = View.GONE
//            text_no_driver_found.visibility = View.VISIBLE
        }
    }
}