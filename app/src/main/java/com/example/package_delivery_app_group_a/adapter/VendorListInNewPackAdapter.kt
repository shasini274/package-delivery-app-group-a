package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.ui.register.RegisterActivity
import com.example.package_delivery_app_group_a.models.Vendor
import kotlinx.android.synthetic.main.vendor_items_row.view.*
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackVendorListFragment
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackVendorListFragmentDirections
import kotlinx.android.synthetic.main.vendor_items_row.view.*

open class VendorListInNewPackAdapter(
    private val context: Context,
    private var list: ArrayList<Vendor>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
// END

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.vendor_items_row,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            holder.itemView.vendor_item.text = model.vendorName

            holder.itemView.vendor_item.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(NewPackVendorListFragmentDirections.actionNewPackVendorListFragmentToNewPackageFragment(
                    1,
                    model.vendor_id,
                    model.vendorName
                    ))
            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
