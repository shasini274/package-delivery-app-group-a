package com.example.package_delivery_app_group_a

import android.app.Dialog
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private lateinit var progDialogBar: Dialog
    fun showProgBar() {
        progDialogBar = Dialog(requireActivity())
        progDialogBar.setContentView(R.layout.app_progress_dialog)
        progDialogBar.setCancelable(false)
        progDialogBar.setCanceledOnTouchOutside(false)
        progDialogBar.show()
    }
 fun hideShowProgBar() {
        progDialogBar.dismiss()
    }

}