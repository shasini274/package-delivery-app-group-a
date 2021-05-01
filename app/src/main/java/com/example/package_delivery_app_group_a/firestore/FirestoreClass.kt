package com.example.package_delivery_app_group_a.firestore
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.package_delivery_app_group_a.models.User
import com.example.package_delivery_app_group_a.ui.driver.DriverMainActivity
import com.example.package_delivery_app_group_a.ui.driver.DriverProfileActivity
import com.example.package_delivery_app_group_a.ui.login.LoginActivity
import com.example.package_delivery_app_group_a.ui.manager.ManagerMainActivity
import com.example.package_delivery_app_group_a.ui.register.RegisterActivity
import com.example.package_delivery_app_group_a.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()
    fun registerUser(activity: RegisterActivity, userInfo: User) {

        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFireStore.collection(Constants.USERS)
                // Document ID for users fields. Here the document it is the User ID.
                .document(userInfo.id)
                // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activity.userRegistrationSuccess()
                }
                .addOnFailureListener { e ->
                    activity.hideShowProgBar()
                    Log.e(activity.javaClass.simpleName,"Error while registering the user.",e)
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
    fun updateDriverProfileData(activity: Activity, userHashMap: HashMap<String, Any>){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is DriverProfileActivity -> {
                        activity.driverProfileUpdateSuccess()
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
}