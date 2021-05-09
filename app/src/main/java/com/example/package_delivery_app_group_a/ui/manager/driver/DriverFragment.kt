package com.example.package_delivery_app_group_a.ui.manager.driver

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DriverFragment : Fragment() {

    private lateinit var driverViewModel: BuildingViewModel

    private lateinit var viewModel: BuildingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        driverViewModel =
            ViewModelProvider(this).get(BuildingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_building, container, false)
        val textView: TextView = root.findViewById(R.id.text_no_building_found)
        driverViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val itemType: String = "New Driver"
        val fab: FloatingActionButton = root.findViewById(R.id.floating_action_btn_1)

        fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(DriverFragmentDirections.actionNavDriverToAddNewDriverFragment())
        }

        return root
    }

}