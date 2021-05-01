package com.example.package_delivery_app_group_a.ui.driver.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.package_delivery_app_group_a.R

class DriverHomeFragment : Fragment() {

    companion object {
        fun newInstance() = DriverHomeFragment()
    }

    private lateinit var viewModel: DriverHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_driver, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriverHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}