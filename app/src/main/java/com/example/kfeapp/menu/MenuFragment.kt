package com.example.kfeapp.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentLoginBinding
import com.example.kfeapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {


    private lateinit var binding: FragmentMenuBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(
            inflater,
            R.layout.fragment_menu, container, false
        )

        sharedViewModel.userName.observe(viewLifecycleOwner, { name ->
            binding.tvUser.setText(name)
        })

        binding.btnMenuOrder.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_menu_to_drink)
        }

        return binding.root
    }
}
