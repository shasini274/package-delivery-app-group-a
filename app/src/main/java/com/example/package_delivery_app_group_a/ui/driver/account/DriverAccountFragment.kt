package com.example.package_delivery_app_group_a.ui.driver.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.package_delivery_app_group_a.R

class DriverAccountFragment : Fragment() {

    companion object {
        fun newInstance() = DriverAccountFragment()
    }

    private lateinit var viewModel: DriverAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_driver, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriverAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}