package com.example.package_delivery_app_group_a.firestore
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.package_delivery_app_group_a.models.*
import com.example.package_delivery_app_group_a.ui.driver.DriverMainActivity
import com.example.package_delivery_app_group_a.ui.driver.DriverProfileActivity
import com.example.package_delivery_app_group_a.ui.driver.account.DriverAccountFragment
import com.example.package_delivery_app_group_a.ui.driver.home.DriverHomeFragment
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivity
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackageFragment
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackVendorListFragment
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackDriverListFragment
import com.example.package_delivery_app_group_a.ui.manager.newpackage.NewPackBuildingListFragment
import com.example.package_delivery_app_group_a.ui.manager.account.AccountFragment
import com.example.package_delivery_app_group_a.ui.manager.building.BuildingFragment
import com.example.package_delivery_app_group_a.ui.manager.building.NewBuildingFragment
import com.example.package_delivery_app_group_a.ui.manager.driver.DriverFragment
import com.example.package_delivery_app_group_a.ui.manager.driver.NewDriverFragment
import com.example.package_delivery_app_group_a.ui.manager.home.HomeFragment
import com.example.package_delivery_app_group_a.ui.manager.vendor.NewVendorFragment
import com.example.package_delivery_app_group_a.ui.manager.vendor.VendorFragment
import com.example.package_delivery_app_group_a.ui.register.RegisterActivity
import com.example.package_delivery_app_group_a.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()
//    fun registerUser(activity: RegisterActivity, tempId: String, userHashMap: HashMap<String, Any>) {
    fun registerUser(activity: RegisterActivity, userInfo: User) {

        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFireStore.collection(Constants.USERS)
                // Document ID for users fields. Here the document it is the User ID.
                .document(userInfo.id)
                .set(userInfo, SetOptions.merge())
                // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
//                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activity.userRegistrationSuccess()
                }
                .addOnFailureListener { e ->
                    activity.hideShowProgBar()
                    Log.e(activity.javaClass.simpleName,"Error while registering the user.",e)
                }
    }
    fun checkDriverExist(activity: RegisterActivity, email: String){
        mFireStore.collection(Constants.DRIVERS)
            .document(email)
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())
                if (document.exists()) {
                    activity.driverDetailSuccess(1)
                }
                else{
                    activity.driverDetailSuccess(0)
                }
            }
            .addOnFailureListener { e ->
                activity.driverDetailSuccess(0)
            }
    }
    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }
    fun getUserDetails(activity: Activity) {

        // Here we pass the collection name from which we wants the data.
        mFireStore.collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(activity.javaClass.simpleName, document.toString())

                // Here we have received the document snapshot which is converted into the User Data model object.
                val user = document.toObject(User::class.java)!!
                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.PKG_APP_PREFERENCES,
                        Context.MODE_PRIVATE
                    )
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_USERNAME,
                    "${user.firstName} ${user.lastName}"
                )
                editor.apply()
                when (activity) {
                    is LoginActivity -> {
                        // Call a function of base activity for transferring the result to it.
                        activity.userLoggedInSuccess(user)
                    }
                    is ManagerMainActivity -> {
                        activity.userDetailSuccess(user)
                    }
                    is DriverMainActivity -> {
                        activity.userDetailSuccess(user)
                    }
//                    is RegisterActivity -> {
//                        activity.userDetailSuccess(user)
//                    }
                }

            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is LoginActivity -> {
                        activity.hideShowProgBar()
                    }
                    is DriverMainActivity -> {
                        activity.hideShowProgBar()
                    }
                    is ManagerMainActivity -> {
                        activity.hideShowProgBar()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting user details.",
                    e
                )
            }
    }
    fun getUserDetailsinFragments(fragment: Fragment) {

        // Here we pass the collection name from which we wants the data.
        mFireStore.collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                // Here we have received the document snapshot which is converted into the User Data model object.
                val user = document.toObject(User::class.java)!!

                when (fragment) {
                    is AccountFragment -> {
                        // Call a function of base activity for transferring the result to it.
                        fragment.userDetailSuccess(user)
                    }
                    is DriverAccountFragment -> {
                        // Call a function of base activity for transferring the result to it.
                        fragment.userDetailSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error. And print the error in log.
                when (fragment) {
                    is AccountFragment -> {
                        fragment.hideShowProgBar()
                    }
                    is DriverAccountFragment -> {
                        fragment.hideShowProgBar()
                    }
                }
            }
    }
    fun updateDriverProfileData(activity: Activity, userHashMap: HashMap<String, Any>, driverEmail: String){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                mFireStore.collection(Constants.DRIVERS)
                    .document(driverEmail)
                    .update(userHashMap)
                    .addOnSuccessListener {
                    when (activity) {
                        is DriverProfileActivity -> {
                            activity.driverProfileUpdateSuccess()
                        }
                    }
                }
//                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is DriverProfileActivity -> {
                        activity.hideShowProgBar()
                    }

                }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the user details.",
                    e
                )
            }
//        mFireStore.collection(Constants.DRIVERS)
//            .document(userHashMap[Constants.EMAIL] as String)
//            .update(userHashMap)
//            .addOnSuccessListener {
//                when (activity) {
//                    is DriverProfileActivity -> {
//                        activity.driverProfileUpdateSuccess()
//                    }
//                }
////                activity.userRegistrationSuccess()
//            }
//            .addOnFailureListener { e ->
//                when (activity) {
//                    is DriverProfileActivity -> {
//                        activity.hideShowProgBar()
//                    }
//
//                }
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while updating the user details.",
//                    e
//                )
//            }
    }
    fun uploadImageCloudStorage(activity: Activity, imageUri: Uri?) {

        //the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Constants.USER_PROFILE_IMAGE + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(
                activity,
                imageUri
            )
        )
        //uploading the file to reference
        sRef.putFile(imageUri!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())

                        when (activity) {
                            is DriverProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                        }
                    }
            }
            .addOnFailureListener { exception ->
                when (activity) {
                    is DriverProfileActivity -> {
                        activity.hideShowProgBar()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }
    fun addDriver(fragment: NewDriverFragment, driverInfo: Driver){
        mFireStore.collection(Constants.DRIVERS)
            // Document ID for users fields. Here the document it is the User ID.
//            .document(driverInfo.id)
            .document(driverInfo.id)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(driverInfo, SetOptions.merge())
            .addOnSuccessListener {
                fragment.driverRegistrationSuccess()
            }
            .addOnFailureListener { e ->
//                activity.hideShowProgBar()
                Log.e(fragment.javaClass.simpleName,"Error while registering the user.",e)
            }

    }
    fun addBuilding(fragment: NewBuildingFragment, buildingInfo: BuildingSite){
        mFireStore.collection(Constants.BUILDINGSITES)
            // Document ID for users fields. Here the document it is the User ID.
            .document()
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(buildingInfo, SetOptions.merge())
            .addOnSuccessListener {
                fragment.buildingRegistrationSuccess()
            }
            .addOnFailureListener { e ->
//                activity.hideShowProgBar()
                Log.e(fragment.javaClass.simpleName,"Error while registering the user.",e)
            }

    }
    fun addVendor(fragment: NewVendorFragment, vendorInfo: Vendor){
        mFireStore.collection(Constants.VENDORS)
            // Document ID for users fields. Here the document it is the User ID.
            .document()
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(vendorInfo, SetOptions.merge())
            .addOnSuccessListener {
                fragment.vendorRegistrationSuccess()
            }
            .addOnFailureListener { e ->
//                activity.hideShowProgBar()
                Log.e(fragment.javaClass.simpleName,"Error while registering the user.",e)
            }

    }
//    fun addPackage(fragment: NewPackageFragment, packageInfo: Package){
//        mFireStore.collection(Constants.PACKAGES)
//            // Document ID for users fields. Here the document it is the User ID.
//            .document()
//            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
//            .set(packageInfo, SetOptions.merge())
//            .addOnSuccessListener {
//                fragment.packageRegistrationSuccess()
//            }
//            .addOnFailureListener { e ->
////                activity.hideShowProgBar()
//                Log.e(fragment.javaClass.simpleName,"Error while registering the package.",e)
//            }
//
//    }
//    fun updateStatusPackage(fragment: NewPackageFragment, packageId: String, packageHashMap: HashMap<String, Any>){
//        mFireStore.collection(Constants.PACKAGES)
//            // Document ID for users fields. Here the document it is the User ID.
//            .document(packageId)
//            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
//            .update(packageHashMap)
//            .addOnSuccessListener {
//                when(fragment){
//                    is DriverHomeFragment ->{
//                        fragment.packageUpdateSuccess()
//                    }
//                }
//
//            }
//            .addOnFailureListener { e ->
////                activity.hideShowProgBar()
//                Log.e(fragment.javaClass.simpleName,"Error while registering the package.",e)
//            }
//
//    }
    fun getBuildingList(fragment: Fragment) {
        // The collection name for PRODUCTS
        mFireStore.collection(Constants.BUILDINGSITES)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
                Log.e("Products List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                val buildingSitesList: ArrayList<BuildingSite> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {

                    val buildingSites = i.toObject(BuildingSite::class.java)
                    buildingSites!!.building_id=i.id
//                    buildingSites!!.email = i.id

                    buildingSitesList.add(buildingSites)
                }

                when (fragment) {
                    is BuildingFragment -> {
                        fragment.successBuildingSitesListFromFireStore(buildingSitesList)
                    }
                    is NewPackBuildingListFragment -> {
                        fragment.successBuildingSitesListFromFireStore(buildingSitesList)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error based on the base class instance.
                when (fragment) {
                    is BuildingFragment -> {
                        fragment.hideShowProgBar()
                    }
                }
                Log.e("Get Building List", "Error while getting product list.", e)
            }
    }
    fun getDriverList(fragment: Fragment) {
        // The collection name for PRODUCTS
        mFireStore.collection(Constants.DRIVERS)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
                Log.e("Drivers List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                val driversList: ArrayList<Driver> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {

                    val drivers = i.toObject(Driver::class.java)
                    drivers!!.id=i.id
//                    buildingSites!!.email = i.id

                    driversList.add(drivers)
                }

                when (fragment) {
                    is DriverFragment -> {
                        fragment.successDriverListFromFireStore(driversList)
                    }
                    is NewPackDriverListFragment -> {
                        fragment.successDriverListFromFireStore(driversList)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error based on the base class instance.
                when (fragment) {
                    is DriverFragment -> {
//                        fragment.hideShowProgBar()
                        Log.e("Get DriverList", "Error while getting product list.", e)
                    }
                }
                Log.e("Get DriverList", "Error while getting product list.", e)
            }
    }
    fun getVendorList(fragment: Fragment) {
        // The collection name for PRODUCTS
        mFireStore.collection(Constants.VENDORS)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
//                Log.e("Vendors List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                val vendorsList: ArrayList<Vendor> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {

                    val vendors = i.toObject(Vendor::class.java)
                    vendors!!.vendor_id=i.id
//                    buildingSites!!.email = i.id

                    vendorsList.add(vendors)
                }

//                when (fragment) {
//                    is NewPackageFragment -> {
//                        fragment.successVendorListFromFireStore(vendorsList)
//                    }
//                }
                when (fragment) {
                    is VendorFragment -> {
                        fragment.successVendorListFromFireStore(vendorsList)
                    }
                    is NewPackVendorListFragment -> {
                        fragment.successVendorListFromFireStore(vendorsList)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error based on the base class instance.
                when (fragment) {
                    is NewPackageFragment -> {
//                        fragment.hideShowProgBar()
                        Log.e("Get DriverList", "Error while getting product list.", e)
                    }
                }
                Log.e("Get DriverList", "Error while getting product list.", e)
            }
    }
    fun getPackagePendingList(fragment: Fragment) {

        // The collection name for PRODUCTS
        mFireStore.collection(Constants.PACKAGES)
            .whereEqualTo(Constants.STATUS, 0)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
//                Log.e("Vendors List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                Log.e("Products List", document.documents.toString())
                val packagesList: ArrayList<Package> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {

                    val packages = i.toObject(Package::class.java)
                    packages!!.pacakage_id=i.id
//                    buildingSites!!.email = i.id

                    packagesList.add(packages)
                }

                when (fragment) {
                    is HomeFragment -> {
                        fragment.successPackagePendingListFromFireStore(packagesList)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error based on the base class instance.
                when (fragment) {
                    is HomeFragment -> {
//                        fragment.hideShowProgBar()
                        Log.e("Get DriverList", "Error while getting product list.", e)
                    }
                }
                Log.e("Get DriverList", "Error while getting product list.", e)
            }
    }
    fun getPackageOnWayList(fragment: Fragment) {

        // The collection name for PRODUCTS
        mFireStore.collection(Constants.PACKAGES)
            .whereEqualTo(Constants.STATUS, 1)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
//                Log.e("Vendors List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                Log.e("Products List", document.documents.toString())
                val packagesList: ArrayList<Package> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {
                    val packages = i.toObject(Package::class.java)

                    packages!!.pacakage_id=i.id
//                    buildingSites!!.email = i.id

                    packagesList.add(packages)
                }

                when (fragment) {
                    is HomeFragment -> {
                        fragment.successPackageOnwayListFromFireStore(packagesList)
                    }
                    is DriverHomeFragment -> {
                        fragment.successPackageDriverListFromFireStore(packagesList)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error based on the base class instance.
                when (fragment) {
                    is HomeFragment -> {
//                        fragment.hideShowProgBar()
                        Log.e("Get DriverList", "Error while getting product list.", e)
                    }
                }
                Log.e("Get DriverList", "Error while getting product list.", e)
            }
    }
//    fun getItemList(fragment: Fragment) {
//        // The collection name for PRODUCTS
//        mFireStore.collection(Constants.ITEMS)
//            .get() // Will get the documents snapshots.
//            .addOnSuccessListener { document ->
//
//                // Here we get the list of boards in the form of documents.
//                Log.e("Items List", document.documents.toString())
//
//                // Here we have created a new instance for Products ArrayList.
//                val itemsList: ArrayList<Item> = ArrayList()
//
//                // A for loop as per the list of documents to convert them into Products ArrayList.
//                for (i in document.documents) {
//
//                    val items = i.toObject(Item::class.java)
//                    items!!.item_id=i.id
////                    buildingSites!!.email = i.id
//
//                    itemsList.add(items)
//                }
//
//                when (fragment) {
//                    is NewVendorFragment -> {
//                        fragment.successItemListFromFireStore(itemsList)
//                    }
//                }
//            }
//            .addOnFailureListener { e ->
//                // Hide the progress dialog if there is any error based on the base class instance.
//                when (fragment) {
//                    is NewVendorFragment -> {
//                        fragment.hideShowProgBar()
//                    }
//                }
//                Log.e("Get Item List", "Error while getting product list.", e)
//            }
//    }


}