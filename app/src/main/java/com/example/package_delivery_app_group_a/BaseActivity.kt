package com.example.package_delivery_app_group_a

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_progress_dialog.*

open class BaseActivity : AppCompatActivity() {
    private lateinit var progDialogBar : Dialog

//    fun showProgBar( text: String){
    fun showProgBar(){
        progDialogBar = Dialog(this)
        progDialogBar.setContentView(R.layout.app_progress_dialog)
        progDialogBar.setCancelable(false)
        progDialogBar.setCanceledOnTouchOutside(false)
        progDialogBar.show()
//        progDialogBar.progressBarText.text = text
    }
    fun hideShowProgBar(){
        progDialogBar.dismiss()
    }
    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarError
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }
}