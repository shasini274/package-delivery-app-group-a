package com.example.package_delivery_app_group_a.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.models.Vendor
import kotlinx.android.synthetic.main.vendor_spinner_item.view.*

class VendorListAdapter(context: Context, vendorList: ArrayList<Vendor>):
    ArrayAdapter<Vendor>(context, 0, vendorList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vendor =getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.vendor_spinner_item, parent, false)
//        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.vendor_spinner_item, parent, true)
        view.vendor_item.text = vendor!!.vendorName

        return view
    }
}
