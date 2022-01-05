package com.example.kfeapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentLoginBinding
import com.example.kfeapp.databinding.FragmentRegisterBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.user.User
import java.math.BigInteger
import java.security.MessageDigest

class LoginFragment : Fragment() {

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var binding: FragmentLoginBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )


        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).userDao
        val viewModelFactory = LoginViewModelFactory(dataSource, application)
        val loginViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LoginViewModel::class.java)

        binding.btnLoginRegister.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_register)
        }

        binding.btnLoginLogin.setOnClickListener { view: View ->

            binding.apply {
                username = etLoginName.text.toString().lowercase()
                password = etLoginPassword.text.toString()

                if (checkEmpties(username, password) && !userExists(username, loginViewModel)) {
                    val usr = loginViewModel.getUserData(username)
                    if (makeMD5(password) == usr?.password) {
                        Toast.makeText(
                            activity,
                            "Login successful",
                            Toast.LENGTH_LONG
                        ).show()

                        //Assign needed data to shared view mdoel.
                        sharedViewModel.saveName(username)
                        sharedViewModel.saveId(usr.userId)
                        sharedViewModel.login()

                        view.findNavController().navigate(R.id.action_login_to_menu)
                    } else {
                        Toast.makeText(
                            activity,
                            "The password doesn't match",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }


        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel





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
                "All fields are required in order to login.",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    private fun userExists(usr: String, vm: LoginViewModel): Boolean {
        val names = vm.database.getAllNames()

        for (n in names) {
            if (n == usr) {
                return false
            }
        }
        Toast.makeText(
            activity,
            "THat user don't exist.",
            Toast.LENGTH_LONG
        ).show()
        return true
    }
}
