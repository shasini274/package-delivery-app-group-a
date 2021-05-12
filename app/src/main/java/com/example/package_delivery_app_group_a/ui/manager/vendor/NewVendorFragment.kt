package com.example.package_delivery_app_group_a.ui.manager.vendor

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.adapter.DriverListAdapter
import com.example.package_delivery_app_group_a.adapter.ItemListAdapter
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.models.Item
import com.example.package_delivery_app_group_a.models.Vendor
import com.example.package_delivery_app_group_a.ui.manager.building.NewBuildingViewModel
import com.example.package_delivery_app_group_a.ui.manager.driver.NewDriverFragmentDirections
import com.example.package_delivery_app_group_a.ui.manager.home.HomeFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_building.*
import kotlinx.android.synthetic.main.fragment_new_vendor.*

class NewVendorFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewVendorFragment()
    }

    private lateinit var viewModel: NewVendorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_vendor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewVendorViewModel::class.java)
        val vendName = view.findViewById<EditText>(R.id.vend_name)
        val vendAddress = view.findViewById<EditText>(R.id.vend_address)
        val vendEmail = view.findViewById<EditText>(R.id.vend_email)
        val vendPerson = view.findViewById<EditText>(R.id.vend_co_person)
        val vendNumber = view.findViewById<EditText>(R.id.vend_number)
        val addVendBtn = view.findViewById<Button>(R.id.vend_btn)
//        val vendItem: RadioButton = view.findViewById(radio_group.checkedRadioButtonId)
//        val vendItem = when(radio_group.checkedRadioButtonId){
//            R.id.ven_typ_1 -> ven_typ_1.text
//            R.id.ven_typ_2 -> ven_typ_2.text
//            R.id.ven_typ_3 -> ven_typ_3.text
//            R.id.ven_typ_4 -> ven_typ_4.text
//            R.id.ven_typ_5 -> ven_typ_5.text
//            R.id.ven_typ_6 -> ven_typ_6.text
//            R.id.ven_typ_7 -> ven_typ_7.text
//            R.id.ven_typ_8 -> ven_typ_8.text
//            else -> ven_typ_9.text
//               }
//            println(vendItem)
//        val vendItem = radio_button_click(view)


        addVendBtn.setOnClickListener{
            println("HEllooooo")
            //            val appContext = context?.applicationContext
            //            Toast.makeText(appContext, "Hello", Toast.LENGTH_LONG).show()
            addVendorSite(
                vendName.text.toString(),
                vendAddress.text.toString(),
                vendEmail.text.toString(),
                vendPerson.text.toString(),
                vendNumber.text.toString(),
                view
            )
//                vendItem.toString()
        }

    }
    private fun addVendorSite(vendName: String, vendAddress: String, vendEmail: String, vendPerson:String, vendNumber:String, view: View){
//        println("HEllooooo1")
//        println(buildName)
//        val dFname = drivFname.text.trim().toString()
//        val dLname = drivLname.text.trim().toString()
//        val dEmail = drivEmail.text.trim().toString()

        if (checkLayoutInputs(vendName, vendAddress, vendEmail, vendPerson, vendNumber)) {
//            showProgBar()
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
            val vendNum = vendNumber.toLong()
            val vendor = Vendor(
                vendName,
                vendAddress,
                vendEmail, vendPerson, vendNum)
            FirestoreClass().addVendor(this, vendor)
            Navigation.findNavController(view).navigate(NewVendorFragmentDirections.actionNewVendorFragmentToNavVendor())
        }
    }
    private fun checkLayoutInputs(vendName: String, vendAddress: String, vendEmail: String, vendPerson:String, vendNumber:String): Boolean {
//        hideShowProgBar()
        return when {
            vendName.isEmpty() && vendAddress.isEmpty() && vendEmail.isEmpty() && vendPerson.isEmpty() && vendNumber.isEmpty()-> {
                println("Input Required")
//                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//                showErrorSnackBar(resources.getString(R.string.err_msg_input_required), true)
                false
            }
            vendName.isEmpty() -> {
                println("Input driv Fname required")
                false
            }
            vendAddress.isEmpty() -> {
                println("Input driv Lname required")
                false
            }
            vendEmail.isEmpty() -> {
                println("Input driv Email required")
                false
            }
            vendPerson.isEmpty() -> {
                println("Input driv Email required")
                false
            }
            vendNumber.isEmpty() -> {
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
//    private fun radio_button_click(view: View): CharSequence? {
//        // Get the clicked radio button instance
//        val radio: RadioButton = view.findViewById(radio_group.checkedRadioButtonId)
//        return radio.text
//    }
    fun vendorRegistrationSuccess(){
        // Hide the progress dialog
//        hideShowProgBar()
//        showErrorSnackBar(resources.getString(R.string.not_err_details), false)
        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
//        finish()
    }


//    override fun onResume() {
//        super.onResume()
//        getItemListFromFireStore()
//    }
//
//    fun successItemListFromFireStore(itemsList: ArrayList<Item>) {
//        // Hide Progress dialog.
//        hideShowProgBar()
//
//        if (itemsList.size > 0) {
//            rv_building_list_items.visibility = View.VISIBLE
//            text_no_building_found.visibility = View.GONE
//
//            rv_building_list_items.layoutManager = LinearLayoutManager(activity)
//            rv_building_list_items.setHasFixedSize(true)
//
//            // TODO Step 7: Pass the third parameter value.
//            // START
//            val adapterItems =
//                ItemListAdapter(requireActivity(), itemsList)
//            // END
//            rv_building_list_items.adapter = adapterItems
//        } else {
//            rv_building_list_items.visibility = View.GONE
//            text_no_building_found.visibility = View.VISIBLE
//        }
//
//    }
//    private fun getItemListFromFireStore() {
//        showProgBar()
//        FirestoreClass().getItemList(this)
//    }

}