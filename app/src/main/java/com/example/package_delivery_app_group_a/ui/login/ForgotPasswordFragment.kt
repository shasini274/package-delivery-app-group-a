package com.example.package_delivery_app_group_a.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.package_delivery_app_group_a.R
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ForgotPasswordFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fgt_button = view.findViewById<Button>(R.id.fgt_submit)
        val fgt_email = view.findViewById<EditText>(R.id.fgt_email)
        fgt_button.setOnClickListener {
            val appContext = context?.applicationContext
            val email = fgt_email.text.trim().toString()

            if (email.isEmpty()){
                Toast.makeText(appContext,R.string.err_msg_enter_email, Toast.LENGTH_LONG).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(appContext,R.string.suc_msg_email_sent, Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.nav_login)
                        }else {
                            // If the registering is not successful then show error message.
                            Toast.makeText(appContext,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}