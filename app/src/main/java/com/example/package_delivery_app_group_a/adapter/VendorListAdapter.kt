package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.Vendor
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingFragmentDirections
import com.example.package_delivery_app_group_a.ui.manager.vendor.VendorFragmentDirections
import kotlinx.android.synthetic.main.vendor_items_row.view.*

open class VendorListAdapter(
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
                Navigation.findNavController(view).navigate(
                    VendorFragmentDirections.actionNavVendorToVendorProfileFragment(
                    model.vendorName,
                    model.address,
                    model.email,
                    model.contactPerson,
                    model.contactNumber,
                    model.vendor_id))

            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
