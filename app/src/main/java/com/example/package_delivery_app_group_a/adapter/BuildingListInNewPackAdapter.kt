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
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackBuildingListFragmentDirections
import com.example.package_delivery_app_group_a.utils.GlideLoader
import kotlinx.android.synthetic.main.building_items_row.view.*

//import kotlinx.android.synthetic.main.item_list_layout.view.*

/**
 * A adapter class for products list items.
 */
// TODO Step 6: Add the parameter as products fragment as we cannot call the delete function of products fragment on the delete button click.
// START
open class BuildingListInNewPackAdapter(
    private val context: Context,
    private var list: ArrayList<BuildingSite>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
// END

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.building_items_row,
                parent,
                false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            holder.itemView.building_item.text = model.siteName

            // TODO Step 4: Assigning the click event to the delete button.
            // START
            holder.itemView.building_item.setOnClickListener {view ->
                Navigation.findNavController(view).navigate(NewPackBuildingListFragmentDirections.actionNewPackBuildingListFragmentToNewPackageFragment(
                    3,
                    model.building_id,
                    model.siteName
                ))
            }
            // END
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
