package com.example.package_delivery_app_group_a.ui.driver

import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Package
import com.example.package_delivery_app_group_a.models.Vendor
import com.example.package_delivery_app_group_a.ui.manager.PackageStatusFragmentArgs
import com.example.package_delivery_app_group_a.utils.Constants
import kotlinx.android.synthetic.main.fragment_package_status_driver.*


class PackageStatusDriverFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_package_status_driver, container, false)
//        val packageStatus = 3  // 1,2,3,4



/*
        val textId: TextView = root.findViewById(R.id.package_detail_number_driver)
        textId.text = FirestoreClass().getCurrentUserID()
*/
//
//        val checkboxAccept: CheckBox = root.findViewById(R.id.check_status_pending_driver)
//        val checkboxDispatch: CheckBox = root.findViewById(R.id.check_status_dispatched_driver)
//        val checkboxDeliver: CheckBox = root.findViewById(R.id.check_status_delivered_driver)
//        val acceptedTV: TextView = root.findViewById(R.id.package_status_accepted)
//        val dispatchedTV: TextView = root.findViewById(R.id.package_status_dispatched)
//        val deliveredTV: TextView = root.findViewById(R.id.package_status_delivered)
//
//        when(packageStatus) {
//            1 -> {
//                checkboxAccept.isClickable = true
//                checkboxDispatch.isClickable = false
//                checkboxDeliver.isClickable = false
//                checkboxAccept.setOnClickListener {
//                    checkboxAccept.visibility = View.GONE
//                    acceptedTV.visibility = View.VISIBLE
//                    //changePackageStatus(2)
//                }
//            }
//            2 -> {
//                checkboxDispatch.isClickable = true
//                checkboxDeliver.isClickable = false
//                checkboxAccept.visibility = View.GONE
//                acceptedTV.visibility = View.VISIBLE
//                checkboxDispatch.setOnClickListener {
//                    checkboxDispatch.visibility = View.GONE
//                    dispatchedTV.visibility = View.VISIBLE
//                    //changePackageStatus(3)
//                }
//            }
//            3 -> {
//                checkboxDeliver.isClickable = true
//                checkboxAccept.visibility = View.GONE
//                acceptedTV.visibility = View.VISIBLE
//                checkboxDispatch.visibility = View.GONE
//                dispatchedTV.visibility = View.VISIBLE
//                checkboxDeliver.setOnClickListener {
//                    checkboxDeliver.visibility = View.GONE
//                    deliveredTV.visibility = View.VISIBLE
//                    //changePackageStatus(4)
//                }
//            }
//            4 -> {
//                checkboxAccept.visibility = View.GONE
//                acceptedTV.visibility = View.VISIBLE
//                checkboxDispatch.visibility = View.GONE
//                dispatchedTV.visibility = View.VISIBLE
//                checkboxDeliver.visibility = View.GONE
//                deliveredTV.visibility = View.VISIBLE
//            }
//        }

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: PackageStatusDriverFragmentArgs by navArgs()
//
        val vendorId = args.packageVendorDriverId
        val buildingId = args.packageBuildingDriverId
        val packageId = args.packagePackageDriverId

        getPackageDetailsFromFirestore(vendorId, buildingId, packageId)
        job_accept_button.setOnClickListener{
            val packageHashMap = HashMap<String, Any>()
            packageHashMap[Constants.STATUS] = 1
            FirestoreClass().updateStatusPackage(this, packageId, packageHashMap)
        }
        job_picked_up.setOnClickListener{
            val packageHashMap = HashMap<String, Any>()
            packageHashMap[Constants.STATUS] = 2
            FirestoreClass().updateStatusPackage(this, packageId, packageHashMap)
        }
        job_drop_off.setOnClickListener{
            val packageHashMap = HashMap<String, Any>()
            packageHashMap[Constants.STATUS] = 3

            FirestoreClass().updateStatusPackage(this, packageId, packageHashMap)
        }
    }
    private fun getPackageDetailsFromFirestore(vendorId: String, buildingId: String, packageId: String) {
        showProgBar()
        FirestoreClass().getPackageDetailDriver(this, vendorId, buildingId, packageId)
    }
    fun buildingInSuccess(building: BuildingSite){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)

        dBuildingName.text = "${building.siteName}"
        dBuildingAdr.text = "${building.address}"
        dBuildingNum.text= "${building.contactNumber}"
        dBuildingCo.text="${building.contactPerson}"
        dbEmail.text="${building.email}"
    }
    fun vendorInSuccess(vendor: Vendor){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)

        dVendorName.text = "${vendor.vendorName}"
        dVendorAdr.text = "${vendor.address}"
        dVendorNum.text= "${vendor.contactNumber}"
        dVendorCo.text="${vendor.contactPerson}"
        dVendorEmail.text="${vendor.email}"
    }
    fun packageUpdateSuccess(){
        FirestoreClass().getCheckStatusPackage(this)
//        status.text = "Accepted"
//        package_status_detail_driver.setBackgroundResource(R.color.orange)
    }
    fun packageInSuccess(packageSt:Package){
        if (packageSt.status == 0){
            status.text = "New Task"
            package_status_detail_driver.setBackgroundResource(R.color.statuscolor)

        }
        if (packageSt.status == 1){
            status.text = "Accepted"
            package_status_detail_driver.setBackgroundResource(R.color.orange)

        }
        if (packageSt.status == 2){
            status.text = "On the way"
//            package_status_detail_driver.setBackgroundResource(R.color.dashboard_item_details_bg)
            Vendor_details.setBackgroundResource(R.color.orange)
        }
        if (packageSt.status == 3){
            status.text = "Delivered"
//            Vendor_details.setBackgroundResource(R.color.dashboard_item_details_bg)
            Building_details.setBackgroundResource(R.color.colorSnackBarSuccess)

        }
//        else{
//            Building_details.setBackgroundResource(R.color.dashboard_item_details_bg)
//            Vendor_details.setBackgroundResource(R.color.dashboard_item_details_bg)
//            package_status_detail_driver.setBackgroundResource(R.color.dashboard_item_details_bg)
//        }
    }
//    fun packageInSuccess(packaged: Package){
//        hideShowProgBar()
//        //val imgHolder = findViewById(R.id.imageView)
//        if (packaged.status == 0){
//            status.text = "New Task"
//        }
//        if (packaged.status == 1){
//            status.text = "Accepted"
//        }
//        if (packaged.status == 2){
//            status.text = "On The Way"
//        }
//        if (packaged.status == 3){
//            status.text = "Delivered"
//        }
//
//    }


}