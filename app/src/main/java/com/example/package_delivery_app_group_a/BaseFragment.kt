package com.example.package_delivery_app_group_a

import android.app.Dialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {

    /**
     * This is a progress dialog instance which we will initialize later on.
     */
    private lateinit var progDialogBar: Dialog
    // END

    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    fun showProgBar() {
        progDialogBar = Dialog(requireActivity())
        progDialogBar.setContentView(R.layout.app_progress_dialog)
        progDialogBar.setCancelable(false)
        progDialogBar.setCanceledOnTouchOutside(false)
        progDialogBar.show()
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideShowProgBar() {
        progDialogBar.dismiss()
    }
//    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
//        val snackBar =
//            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
//        val snackBarView = snackBar.view
//
//        if (errorMessage) {
//            snackBarView.setBackgroundColor(
//                ContextCompat.getColor(
//                    this @BaseFragment,
//                    R.color.colorSnackBarError
//                )
//            )
//        }else{
//            snackBarView.setBackgroundColor(
//                ContextCompat.getColor(
//                    this@BaseActivity,
//                    R.color.colorSnackBarSuccess
//                )
//            )
//        }
//        snackBar.show()
//    }
}