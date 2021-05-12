package com.example.package_delivery_app_group_a.ui.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.package_delivery_app_group_a.BaseFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.firestore.FirestoreClass
import com.example.package_delivery_app_group_a.models.BuildingSite
import com.example.package_delivery_app_group_a.models.Driver
import com.example.package_delivery_app_group_a.models.Package
import com.example.package_delivery_app_group_a.models.Vendor
//import com.example.package_delivery_app_group_a.ui.manager.PackageStatusFragmentArgs
import kotlinx.android.synthetic.main.fragment_package_status.*


class PackageStatusFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_package_status, container, false)

//        val status1: TextView = root.findViewById(R.id.package_status_driver_1step)
//        val status2: TextView = root.findViewById(R.id.package_status_driver_2step)
//        val status3: TextView = root.findViewById(R.id.package_status_driver_3step)

        val args: PackageStatusFragmentArgs by navArgs()

        val vendorId = args.packageVendorId
        val buildingId = args.packageBuildingId
        val driverId = args.packageDriverId
        val packageId = args.packageId

        getPackageDetailsFromFirestore(vendorId, buildingId, driverId, packageId)




//        when(args.packageStatus) {
//            //Pending
//            1 -> {
//
//            }
//            //Accepted
//            2 -> {
//                status1.text = "Accepted"
//                status1.setBackgroundResource(R.color.grey_very_light)
//            }
//            //On the way to vendor
//            3 -> {
//                status1.text = "Accepted"
//                status1.setBackgroundResource(R.color.grey_very_light)
//                status2.text = "Dispatched"
//                status2.setBackgroundResource(R.color.grey_very_light)
//            }
//            //On the way to building
//            4 -> {
//                status1.text = "Accepted"
//                status1.setBackgroundResource(R.color.grey_very_light)
//                status2.text = "Dispatched"
//                status2.setBackgroundResource(R.color.grey_very_light)
//                status3.text = "Delivered"
//                status3.setBackgroundResource(R.color.grey_very_light)
//            }
//        }
        return root
    }
//    override fun onResume() {
//        super.onResume()
//        getPackageDetailsFromFirestore()
//    }
    private fun getPackageDetailsFromFirestore(vendorId: String, buildingId: String, driverId: String, packageId: String) {
        showProgBar()
        FirestoreClass().getPackageDetail(this, vendorId, buildingId,driverId, packageId)
    }
    fun buildingInSuccess(building: BuildingSite){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)

        packbuilName.text = "${building.siteName}"
        packbuiAddr.text = "${building.address}"
        packbuiCoNumber.text= "${building.contactNumber}"
        packbuiCoPerson.text="${building.contactPerson}"
        packbuiEmail.text="${building.email}"
    }
    fun vendorInSuccess(vendor: Vendor){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)

        packVendName.text = "${vendor.vendorName}"
        packVendAddr.text = "${vendor.address}"
        packVendCoNumber.text= "${vendor.contactNumber}"
        packVendCoPerson.text="${vendor.contactPerson}"
        packVendEmail.text="${vendor.email}"
    }
    fun driverInSuccess(driver: Driver){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)

        packDriName.text = "${driver.firstName} ${driver.lastName}"
        packCoNumber.text= "${driver.mobile}"
        packDriEmail.text="${driver.id}"
    }
    fun packageInSuccess(packaged: Package){
        hideShowProgBar()
        //val imgHolder = findViewById(R.id.imageView)
        if (packaged.status == 0){
            package_status.text = "Pending"
        }
        if (packaged.status == 1){
            package_status.text = "Accepted"
        }
        if (packaged.status == 2){
            package_status.text = "On The Way"
        }
        if (packaged.status == 3){
            package_status.text = "Delivered"
        }

    }

}