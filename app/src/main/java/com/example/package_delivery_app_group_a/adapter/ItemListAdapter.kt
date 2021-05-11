package com.example.package_delivery_app_group_a.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.data.model.Affirmation
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.models.Item
import com.example.package_delivery_app_group_a.ui.manager.driver.DriverFragment
import kotlinx.android.synthetic.main.driver_items_row.view.*
import kotlinx.android.synthetic.main.driver_items_row.view.driver_item
import kotlinx.android.synthetic.main.item_items_row.view.*

class ItemListAdapter (
    private val context: Context,
    private var list: ArrayList<Item>
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
                R.layout.item_items_row,
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
        val model1 = list[position]
        val model2 = list[position + 1]
        val model3 = list[position + 2]

        if (holder is MyViewHolder) {

            holder.itemView.item_type_1.text = model1.itemName

            // TODO Step 4: Assigning the click event to the delete button.
            // START
//            holder.itemView.driver_item.setOnClickListener {
//
//                // TODO Step 8: Now let's call the delete function of the ProductsFragment.
//                // START
//
//                // END
//            }
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