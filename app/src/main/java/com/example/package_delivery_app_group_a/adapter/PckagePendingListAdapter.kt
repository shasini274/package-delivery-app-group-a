package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.Package

class PackagePendingListAdapter (
    private val context: Context,
    private var list: ArrayList<Package>
): RecyclerView.Adapter<PackagePendingListAdapter.PackagePendingViewHolder>(){

    class PackagePendingViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val vendorTextView: TextView = view.findViewById(R.id.home_pending_vendor_text)
        val buildingTextView: TextView = view.findViewById(R.id.home_pending_building_text)
        val driverTextView: TextView = view.findViewById(R.id.home_pending_driver_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagePendingListAdapter.PackagePendingViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.package_list_pending,parent,false)
        return PackagePendingListAdapter.PackagePendingViewHolder(adapterLayout)

    }
    override fun onBindViewHolder(holder: PackagePendingListAdapter.PackagePendingViewHolder, position: Int) {
        val model = list[position]
        holder.vendorTextView.text = model.vendor_id
        holder.buildingTextView.text = model.building_id
        holder.driverTextView.text = model.driver_id


    }
    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}