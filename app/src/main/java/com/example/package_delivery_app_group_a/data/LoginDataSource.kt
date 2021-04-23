package com.example.package_delivery_app_group_a.data

import android.util.Log
import com.example.package_delivery_app_group_a.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var auth: FirebaseAuth;
    fun login(username: String, password: String): Result<LoggedInUser> {
        auth = FirebaseAuth.getInstance();
        val task = auth.createUserWithEmailAndPassword(username, password)
        try {
            // TODO: handle loggedInUser authentication

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }



    fun logout() {
        // TODO: revoke authentication
    }
}