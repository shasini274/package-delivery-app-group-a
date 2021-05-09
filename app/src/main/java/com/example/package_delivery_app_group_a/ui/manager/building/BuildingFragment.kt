package com.example.package_delivery_app_group_a.ui.manager.building

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
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BuildingFragment : BaseFragment() {
    private lateinit var buildingViewModel: BuildingViewModel



    private lateinit var viewModel: BuildingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buildingViewModel =
            ViewModelProvider(this).get(BuildingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_building, container, false)
        val textView: TextView = root.findViewById(R.id.text_building)
        buildingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val itemType: String = "New Building"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)

        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(BuildingFragmentDirections.actionNavBuildingToAddNewBuildingFragment(itemType))
        }

        return root
    }
    fun successBuildingSitesListFromFireStore(buildingSitesList: ArrayList<BuildingSite>) {

        // Hide Progress dialog.
        hideShowProgBar()

        if (buildingSitesList.size > 0) {
            rv_my_product_items.visibility = View.VISIBLE
            tv_no_products_found.visibility = View.GONE

            rv_my_product_items.layoutManager = LinearLayoutManager(activity)
            rv_my_product_items.setHasFixedSize(true)

            // TODO Step 7: Pass the third parameter value.
            // START
            val adapterProducts =
                MyBuildingListAdapter(requireActivity(), buildingSitesList, this@BuildingFragment)
            // END
            rv_my_product_items.adapter = adapterProducts
        } else {
            rv_my_product_items.visibility = View.GONE
            tv_no_products_found.visibility = View.VISIBLE
        }
    }

}