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
import com.example.package_delivery_app_group_a.ui.driver.home.DriverHomeFragmentDirections
import com.example.package_delivery_app_group_a.ui.manager.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.package_list_ontheway.view.*

class PackageListForDriverAdapter (
    private val context: Context,
    private var list: ArrayList<Package>
): RecyclerView.Adapter<PackageListForDriverAdapter.PackageOnWayViewHolder>(){

    class PackageOnWayViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val vendorTextView: TextView = view.findViewById(R.id.home_ontheway_vendor_text)
        val buildingTextView: TextView = view.findViewById(R.id.home_ontheway_building_text)
        val driverTextView: TextView = view.findViewById(R.id.home_ontheway_driver_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageOnWayViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.package_list_ontheway,parent,false)
        return PackageOnWayViewHolder(adapterLayout)

    }
    override fun onBindViewHolder(holder: PackageOnWayViewHolder, position: Int) {
        val model = list[position]
        holder.vendorTextView.text = model.vendorName
        holder.buildingTextView.text = model.buildingName
        holder.driverTextView.text = model.driverName

        holder.itemView.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(DriverHomeFragmentDirections.actionNavHomeDriverToPackageStatusDriverFragment(
                model.vendor_id,
                model.building_id,
                model.pacakage_id
            ))
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}