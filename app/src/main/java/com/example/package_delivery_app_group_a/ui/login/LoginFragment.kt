package com.example.package_delivery_app_group_a.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.package_delivery_app_group_a.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.concurrent.schedule

class LoginFragment : Fragment(){
    private lateinit var navController: NavController
    //private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view_login = inflater.inflate(R.layout.fragment_login, container, false)

        return view_login

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val log_button = view.findViewById<Button>(R.id.login)
        val reg_button = view.findViewById(R.id.login_register) as TextView
        val reg_fpwd_button = view.findViewById(R.id.forgot_pwd) as TextView
        val log_email = view.findViewById<EditText>(R.id.username)
        val log_pwd = view.findViewById<EditText>(R.id.password)


        log_button.setOnClickListener {
            val appContext = context?.applicationContext
            val email = log_email.text.trim().toString()
            val pwd = log_pwd.text.trim().toString()

            if(pwd.isNotEmpty() &&  email.isNotEmpty()){
                //Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()
                signInUser(email, pwd)
            }
            else{
                Toast.makeText(appContext,R.string.err_msg_input_required, Toast.LENGTH_LONG).show()
            }
        }
            reg_button.setOnClickListener{
                findNavController().navigate(R.id.nav_register)}

            reg_fpwd_button.setOnClickListener{
                findNavController().navigate(R.id.nav_fgt_pwd)}
    }
    private fun signInUser(email:String, pwd:String) {
        val appContext = context?.applicationContext
        //val e_mail: String = email { it <= ' ' }
        //val password: String = pwd { it <= ' ' }

        // Create an instance and create a register a user with email and password.
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult> { task ->

                    // If the registration is successfully done
                    if (task.isSuccessful) {

                        // Firebase registered user
                        //val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(appContext,R.string.suc_msg_login, Toast.LENGTH_LONG).show()
                        val user = FirebaseAuth.getInstance().currentUser!!.uid
                        //findNavController().navigate(R.id.nav_login)
                    } else {
                        // If the registering is not successful then show error message.
                        Toast.makeText(appContext,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                })
    }
//    override fun onClick(view: View?) {
//    val appContext = context?.applicationContext
//        if (view != null){
//            when(view.id){
//                R.id.forgot_pwd ->{
//
//                }
//                R.id.login_register ->{
//                    findNavController().navigate(R.id.nav_register)
//                }
//                R.id.login -> {
//                    val log_email = view.findViewById<EditText>(R.id.username)
//                    val log_pwd = view.findViewById<EditText>(R.id.password)
//                    val email = log_email.text.trim().toString()
//                    val pwd = log_pwd.text.trim().toString()
//
//                    if(pwd.isNotEmpty() &&  email.isNotEmpty()){
//                        //Toast.makeText(appContext,R.string.not_err_details, Toast.LENGTH_LONG).show()
//                        signInUser(email, pwd)
//                    }
//                    else{
//                        Toast.makeText(appContext,R.string.err_msg_input_required, Toast.LENGTH_LONG).show()
//                    }
//
//                }
//            }
//        }
//    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        loginViewModel.loginFormState.observe(this,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }*/
}