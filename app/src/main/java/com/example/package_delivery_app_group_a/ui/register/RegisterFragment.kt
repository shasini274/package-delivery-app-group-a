package com.example.package_delivery_app_group_a.ui.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.package_delivery_app_group_a.LaunchFragment
import com.example.package_delivery_app_group_a.R
import com.example.package_delivery_app_group_a.addChildFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sdsmdg.tastytoast.TastyToast
import java.util.*
import kotlin.concurrent.schedule

class RegisterFragment : Fragment() {

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view_register = inflater.inflate(R.layout.fragment_register, container, false)

        return view_register
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reg_button = view.findViewById<Button>(R.id.register)
        val reg_login = view.findViewById(R.id.reg_login) as TextView
        val reg_fname = view.findViewById<EditText>(R.id.reg_fname)
        val reg_lname = view.findViewById<EditText>(R.id.reg_lname)
        val reg_email = view.findViewById<EditText>(R.id.reg_email)
        val reg_pwd = view.findViewById<EditText>(R.id.reg_pwd)
        val reg_cpwd = view.findViewById<EditText>(R.id.reg_cpwd)

        reg_button.setOnClickListener {
            val appContext = context?.applicationContext
            val fname = reg_fname.text.trim().toString()
            val lname = reg_lname.text.trim().toString()
            val pwd = reg_pwd.text.trim().toString()
            val cpwd = reg_cpwd.text.trim().toString()
            val email = reg_email.text.trim().toString()

            if(fname.isNotEmpty() && lname.isNotEmpty() &&  pwd.isNotEmpty() &&  cpwd.isNotEmpty()
                &&  email.isNotEmpty() && pwd == cpwd ){
                //Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()
                registerUser(email, pwd)
            }
            else if (pwd != cpwd){
                Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(appContext,R.string.err_msg_input_required, Toast.LENGTH_LONG).show()
            }
        }
        reg_login.setOnClickListener{
            findNavController().navigate(R.id.nav_login)
        }
    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fieldFragment= BaseFragment()
        addChildFragment(fieldFragment, R.id.fragment_field)
    }*/


    private fun registerUser(email:String, pwd:String) {
        val appContext = context?.applicationContext

        // Create an instance and create a register a user with email and password.
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult> { task ->

                    // If the registration is successfully done
                    if (task.isSuccessful) {
                        // Firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.nav_login)

                    } else {
                        // If the registering is not successful then show error message.
                        Toast.makeText(appContext,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                })
    }


}


/*private fun validateRegisterDetails () {
        if(R.id.reg_fname.toString().isNotEmpty() && R.id.reg_lname.toString().isNotEmpty() &&
            R.id.reg_pwd.toString().isNotEmpty() && R.id.reg_pwd.toString().isNotEmpty() &&
            R.id.reg_pwd.toString() == R.id.reg_cpwd.toString()){
            val appContext = context?.applicationContext
            Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()

        }
        if (R.id.reg_pwd.toString() != R.id.reg_cpwd.toString()) {
            val appContext = context?.applicationContext
            Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
        }else{
            val appContext = context?.applicationContext
            Toast.makeText(appContext,R.string.err_msg_input_required, Toast.LENGTH_LONG).show()
        }
    }*/
    /*private fun validateRegisterDetails(): Boolean {


        return when{
            TextUtils.isEmpty(R.id.reg_fname.toString().trim {it<=' '}) -> {
                val appContext = context?.applicationContext
                Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()
                //showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(R.id.reg_lname.toString().trim {it<=' '}) -> {
                Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
                //showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }
            TextUtils.isEmpty(R.id.reg_pwd.toString().trim {it<=' '}) -> {
                Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
                //showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            TextUtils.isEmpty(R.id.reg_cpwd.toString().trim {it<=' '}) -> {
                Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
                //showErrorSnackBar(resources.getString(R.string.err_msg_enter_cpassword), true)
                false
            }
            R.id.reg_pwd.toString().trim {it<=' '} != R.id.reg_cpwd.toString()
                    .trim{it<= ' '} ->{
                    Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()

                //showErrorSnackBar(resources.getString(R.string.err_msg_password_n_cpwd_mismatch), true)
                false
            }
            else -> {
                Toast.makeText(appContext,R.string.err_msg_password_n_cpwd_mismatch, Toast.LENGTH_LONG).show()
                //showErrorSnackBar(resources.getString(R.string.not_err_details), false)
                true
            }
        }
    }*/

