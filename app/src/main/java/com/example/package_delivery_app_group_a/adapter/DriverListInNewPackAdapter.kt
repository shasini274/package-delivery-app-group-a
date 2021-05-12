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
import kotlinx.android.synthetic.main.building_items_row.view.*
import kotlinx.android.synthetic.main.building_items_row.view.building_item
import kotlinx.android.synthetic.main.driver_items_row.view.*
import androidx.navigation.Navigation
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackDriverListFragment
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackDriverListFragmentDirections

open class DriverListInNewPackAdapter(
    private val context: Context,
    private var list: ArrayList<Driver>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
// END

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.driver_items_row,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            holder.itemView.driver_item.text = model.firstName + model.lastName

            holder.itemView.driver_item.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(NewPackDriverListFragmentDirections.actionNewPackDriverListFragmentToNewPackageFragment(
                    2,
                    model.id,
                    model.firstName + model.lastName
                ))
            }

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}