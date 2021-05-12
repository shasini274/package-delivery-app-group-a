package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.Package
import com.example.package_delivery_app_group_a.ui.manager.history.HistoryFragmentDirections
import com.example.package_delivery_app_group_a.ui.manager.home.HomeFragmentDirections

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
        holder.vendorTextView.text = model.vendorName
        holder.buildingTextView.text = model.buildingName
        holder.driverTextView.text = model.driverName
        holder.itemView.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(
                HomeFragmentDirections.actionNavHomeToPackageStatusFragment(
                    model.vendor_id,
                    model.building_id,
                    model.driver_id,
                    model.pacakage_id))
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}