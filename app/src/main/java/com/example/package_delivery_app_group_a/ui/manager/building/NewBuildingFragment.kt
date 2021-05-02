package com.example.package_delivery_app_group_a.ui.manager.building

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.package_delivery_app_group_a.R

class NewBuildingFragment : Fragment() {

    companion object {
        fun newInstance() = NewBuildingFragment()
    }

    private lateinit var viewModel: NewBuildingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_building, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewBuildingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}