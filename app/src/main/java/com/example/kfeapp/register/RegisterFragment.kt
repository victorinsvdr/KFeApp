package com.example.kfeapp.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kfeapp.R
import com.example.kfeapp.databinding.FragmentLoginBinding
import com.example.kfeapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,container,false)

        binding.btnRegisterRegister.setOnClickListener { view: View ->
            //TODO INSERT USER BEFORE NAGIVATING BACK TO LOGIN.
            view.findNavController().navigate(R.id.action_register_to_login)
        }

        return binding.root
    }
}