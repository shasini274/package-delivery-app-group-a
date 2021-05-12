package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.utils.GlideLoader
import kotlinx.android.synthetic.main.building_items_row.view.*
import kotlinx.android.synthetic.main.building_items_row.view.building_item
import kotlinx.android.synthetic.main.driver_items_row.view.*

open class DriverListAdapter(
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
            GlideLoader(context).loadUserPicture(model.image, holder.itemView.driver_image)
            holder.itemView.driver_item.text = model.firstName + model.lastName

            // TODO Step 4: Assigning the click event to the delete button.
            // START
            holder.itemView.driver_item.setOnClickListener {

                // TODO Step 8: Now let's call the delete function of the ProductsFragment.
                // START

                // END
            }
            // END
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}