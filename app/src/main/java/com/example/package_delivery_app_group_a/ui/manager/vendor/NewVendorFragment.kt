package com.example.package_delivery_app_group_a.ui.manager.vendor

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.package_delivery_app_group_a.R

class NewVendorFragment : Fragment() {

    companion object {
        fun newInstance() = NewVendorFragment()
    }

    private lateinit var viewModel: NewVendorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_vendor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewVendorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}