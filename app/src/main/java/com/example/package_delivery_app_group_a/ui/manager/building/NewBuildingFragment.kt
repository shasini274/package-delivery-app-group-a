package com.example.package_delivery_app_group_a.ui.manager.building

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.ui.manager.driver.NewDriverViewModel
import com.google.firebase.auth.FirebaseAuth

class NewBuildingFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewBuildingFragment()
    }

    private lateinit var viewModel: NewBuildingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_building, container, false)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(NewBuildingViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewBuildingViewModel::class.java)
        val buildName = view.findViewById<EditText>(R.id.build_name)
        val buildAddress = view.findViewById<EditText>(R.id.build_address)
        val buildEmail = view.findViewById<EditText>(R.id.build_email)
        val buildPerson = view.findViewById<EditText>(R.id.build_co_person)
        val buildNumber = view.findViewById<EditText>(R.id.build_number)
        val addBuildBtn = view.findViewById<Button>(R.id.build_btn)

        addBuildBtn.setOnClickListener{
            println("HEllooooo")
    //            val appContext = context?.applicationContext
    //            Toast.makeText(appContext, "Hello", Toast.LENGTH_LONG).show()
            addBuildingSite(
                buildName.text.toString(),
                buildAddress.text.toString(),
                buildEmail.text.toString(),
                buildPerson.text.toString(),
                buildNumber.text.toString()


            )
        }
    }
    private fun addBuildingSite(buildName: String, buildAddress: String, buildEmail: String, buildPerson:String, buildNumber:String ){
        println("HEllooooo1")
        println(buildName)
//        val dFname = drivFname.text.trim().toString()
//        val dLname = drivLname.text.trim().toString()
//        val dEmail = drivEmail.text.trim().toString()

        if (checkLayoutInputs(buildName, buildAddress, buildEmail, buildPerson, buildNumber)) {
            showProgBar()
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
            val building = BuildingSite(
                buildName,
                buildAddress,
                buildEmail, buildPerson, buildNumber)
            FirestoreClass().addBuilding(this, building)
        }
    }
    private fun checkLayoutInputs(buildName: String, buildAddress: String, buildEmail: String, buildPerson:String, buildNumber:String ): Boolean {
        hideShowProgBar()
        return when {
            buildName.isEmpty() && buildAddress.isEmpty() && buildEmail.isEmpty() && buildPerson.isEmpty() && buildNumber.isEmpty()-> {
                println("Input Required")
//                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }
            buildName.isEmpty() -> {
                println("Input driv Fname required")
                false
            }
            buildAddress.isEmpty() -> {
                println("Input driv Lname required")
                false
            }
            buildEmail.isEmpty() -> {
                println("Input driv Email required")
                false
            }
            buildPerson.isEmpty() -> {
                println("Input driv Email required")
                false
            }
            buildNumber.isEmpty() -> {
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
    fun buildingRegistrationSuccess(){
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


