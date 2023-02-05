package com.zexceed.to_doapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.zexceed.to_doapp.R
import com.zexceed.to_doapp.databinding.FragmentSignUpBinding
import com.zexceed.to_doapp.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun registerEvents() {

        binding.authTextView.setOnClickListener {
            navController.navigate(R.id.action_signinFragment_to_signUpFragment)
        }


        binding.nextBtnSignIn.setOnClickListener {
            val email = binding.emaillEt.toString().trim()
            val pass = binding.passEt.toString().trim() //trim for remove extra space

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Login Successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            navController.navigate(R.id.action_signinFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }
        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

}