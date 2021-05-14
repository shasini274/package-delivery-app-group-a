package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingFragmentDirections
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackBuildingListFragmentDirections
import com.example.package_delivery_app_group_a.utils.GlideLoader
import kotlinx.android.synthetic.main.building_items_row.view.*

open class BuildingListAdapter(
    private val context: Context,
    private var list: ArrayList<BuildingSite>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.building_items_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            holder.itemView.building_item.text = model.siteName

            holder.itemView.building_item.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(
                    BuildingFragmentDirections.actionNavBuildingToBuildingProfileFragment(
                    model.siteName,
                    model.address,
                    model.email,
                    model.contactPerson,
                    model.contactNumber,
                    model.building_id))
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}