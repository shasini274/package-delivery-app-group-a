package com.example.package_delivery_app_group_a.ui.manager.newpackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.BuildingListInNewPackAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import kotlinx.android.synthetic.main.new_pack_building_list_fragment.*

class NewPackBuildingListFragment : Fragment() {

    companion object {
        fun newInstance() = NewPackBuildingListFragment()
    }

    private lateinit var listViewModel: NewPackBuildingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_pack_building_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(NewPackBuildingListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume(){
        super.onResume()
        getBuildingListFromFirestore()
    }
    fun successBuildingSitesListFromFireStore(buildingList: ArrayList<BuildingSite>){
//        hideShowProgBar()

        if(buildingList.size>0) {
            rv_new_package_building_list_items.visibility = View.VISIBLE
            text_new_package_no_building_found.visibility = View.GONE

            rv_new_package_building_list_items.layoutManager = LinearLayoutManager(activity)
            rv_new_package_building_list_items.setHasFixedSize(true)

            val adapterBuilding =
                BuildingListInNewPackAdapter(requireActivity(), buildingList)
            rv_new_package_building_list_items.adapter = adapterBuilding
        }
        else{
            rv_new_package_building_list_items.visibility = View.GONE
            text_new_package_no_building_found.visibility = View.VISIBLE
        }
    }

    private fun getBuildingListFromFirestore(){
//        showProgBar()
        FirestoreClass().getBuildingList(this)
    }

}