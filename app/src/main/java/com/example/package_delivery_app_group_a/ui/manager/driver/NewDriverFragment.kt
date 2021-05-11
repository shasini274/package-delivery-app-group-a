package com.example.package_delivery_app_group_a.ui.manager.driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.models.User
import com.google.firebase.auth.FirebaseAuth

class NewDriverFragment : BaseFragment(){
    companion object {
        fun newInstance() = NewDriverFragment()
    }
    private lateinit var viewModel: NewDriverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_driver, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewDriverViewModel::class.java)
        val drivFname = view.findViewById<EditText>(R.id.driv_fname)
        val drivLname = view.findViewById<EditText>(R.id.driv_lname)
        val drivEmail = view.findViewById<EditText>(R.id.driv_email)
        val addDriverBtn = view.findViewById<Button>(R.id.driv_btn)

        addDriverBtn.setOnClickListener{
            println("HEllooooo")
//            val appContext = context?.applicationContext
//            Toast.makeText(appContext, "Hello", Toast.LENGTH_LONG).show()
            addDriver(
                    drivFname.text.toString(),
                    drivLname.text.toString(),
                    drivEmail.text.toString()
            )
        }
    }


    private fun addDriver(drivFname: String, drivLname: String, drivEmail: String){
        println("HEllooooo1")
        println(drivFname)
//        val dFname = drivFname.text.trim().toString()
//        val dLname = drivLname.text.trim().toString()
//        val dEmail = drivEmail.text.trim().toString()

        if (checkLayoutInputs(drivFname, drivLname, drivEmail)) {
            showProgBar()
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
            val driver = Driver(
                drivEmail,
                drivFname,
                drivLname)
            FirestoreClass().addDriver(this, driver)
        }
    }
    private fun checkLayoutInputs(drivFname: String, drivLname: String, drivEmail: String): Boolean {
//        hideShowProgBar()
        return when {

            drivFname.isEmpty() && drivLname.isEmpty() && drivEmail.isEmpty() -> {
                println("Input Required")
//                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }
            drivFname.isEmpty() -> {
                println("Input driv Fname required")
                false
            }
            drivLname.isEmpty() -> {
                println("Input driv Lname required")
                false
            }
            drivEmail.isEmpty() -> {
                println("Input driv Email required")
                false
            }
            else -> {
                println("Ok")
                //showErrorSnackBar(resources.getString(R.string.not_err_details), false)
                true
            }
        }
    }
    fun driverRegistrationSuccess(){
        // Hide the progress dialog
        hideShowProgBar()
//        showErrorSnackBar(resources.getString(R.string.not_err_details), false)
        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
//        finish()
    }

}




