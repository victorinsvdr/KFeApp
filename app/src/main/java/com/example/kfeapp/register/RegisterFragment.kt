package com.example.kfeapp.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kfeapp.R
import com.example.kfeapp.databinding.FragmentLoginBinding
import com.example.kfeapp.databinding.FragmentRegisterBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.user.User
import java.math.BigInteger
import java.security.MessageDigest


class RegisterFragment : Fragment() {

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).userDao
        val viewModelFactory = RegisterViewModelFactory(dataSource, application)
        val registerViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RegisterViewModel::class.java)



        binding.btnRegisterRegister.setOnClickListener { view: View ->
            //TODO INSERT USER BEFORE NAGIVATING BACK TO LOGIN.
            //val usr = User(0, )
            //registerViewModel.database.insertUser()

            //BIND THE DATA WHEN THE BUTTON IS CLICKED, NOT BEFORE.
            binding.apply {
                username = etRegisterName.text.toString().lowercase()
                password = etRegisterPassword.text.toString()

                if (checkEmpties(username, password) && checkDbDupes(username, registerViewModel)) {
                    val usr = User(0, username, makeMD5(password))
                    registerViewModel.insert(usr)
                    Toast.makeText(activity, "$username successfully registered.", Toast.LENGTH_LONG)
                        .show()
                    view.findNavController().navigate(R.id.action_register_to_login)
                }

            }


            //admin - admin; test - test

            //registerViewModel.database.deleteUserById(4)
/*
            for (dbName in registerViewModel.database.getAllNames()) {

                if (dbName.equals(username)) {
                    dupe = !dupe
                    break
                }
            }

            if (dupe)
                Toast.makeText(activity, "User already registered.", Toast.LENGTH_LONG).show()
            else {
                registerViewModel.database.insertUser(usr)


            }*/
        }

        binding.lifecycleOwner = this

        binding.registerViewModel = registerViewModel

        return binding.root
    }


    private fun makeMD5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun checkEmpties(usr: String, pass: String): Boolean {
        if (usr.isEmpty() || pass.isEmpty()) {
            Toast.makeText(
                activity,
                "All fields are required in order to register.",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    private fun checkDbDupes(usrName: String, vm: RegisterViewModel): Boolean {
        val names = vm.database.getAllNames()

        for (n in names) {
            if (n == usrName) {
                Toast.makeText(
                    activity,
                    "User already registered.",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
        }
        return true
    }
}