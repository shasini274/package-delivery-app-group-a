package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.R


class NewPackageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_new_package, container, false)


/*        val args: NewPackageFragmentArgs by navArgs()

        val tvTemp: TextView = root.findViewById(R.id.new_item_test_text2)
        tvTemp.text = args.newItemType

        (activity as AppCompatActivity).supportActionBar?.title = args.newItemType
        */

            return root
    }


}