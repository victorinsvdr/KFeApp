package com.example.kfeapp.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentDrinkBinding
import com.example.kfeapp.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentOrderBinding>(
            inflater,
            R.layout.fragment_order, container, false
        )

        binding.apply {
            tvOrderDrinkName.setText(sharedViewModel.drink.value.toString())
            tvOrderDrinkPrice.setText(sharedViewModel.drinkPrice.value.toString())
            tvOrderFoodName.setText(sharedViewModel.food.value.toString())
            tvOrderFoodPrice.setText(sharedViewModel.foodPrice.value.toString())
            tvOrderDessertName.setText(sharedViewModel.dessert.value.toString())
            tvOrderDessertPrice.setText(sharedViewModel.dessertPrice.value.toString())
        }


        return binding.root
    }
}