package com.example.kfeapp.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentDrinkBinding
import com.example.kfeapp.databinding.FragmentOrderBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.order.Order
import com.example.kfeapp.dessert.DessertViewModel
import com.example.kfeapp.dessert.DessertViewModelFactory

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

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).orderDao
        val viewModelFactory = OrderViewModelFactory(dataSource, application)
        val orderViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(OrderViewModel::class.java)

        val food_n = sharedViewModel.food.value.toString()
        val food_p = sharedViewModel.foodPrice.value?.replace("€", "")?.toDouble()
        val drink_n = sharedViewModel.drink.value.toString()
        val drink_p = sharedViewModel.drinkPrice.value?.replace("€", "")?.toDouble()
        val dessert_n = sharedViewModel.dessert.value.toString()
        val dessert_p = sharedViewModel.dessertPrice.value?.replace("€", "")?.toDouble()

        val total = food_p?.let { dessert_p?.let { it1 -> drink_p?.plus(it)?.plus(it1) } }



        binding.apply {
            tvOrderDrinkName.setText(food_n)
            tvOrderDrinkPrice.setText(food_p.toString())
            tvOrderFoodName.setText(drink_n)
            tvOrderFoodPrice.setText(drink_p.toString())
            tvOrderDessertName.setText(dessert_n)
            tvOrderDessertPrice.setText(dessert_p.toString())

            tvOrderTotal.setText(total.toString() + "€")

            btnOrderSave.setOnClickListener { view: View ->
                val order = food_p?.let {
                    drink_p?.let { it1 ->
                        dessert_p?.let { it2 ->
                            total?.let { it3 ->
                                Order(
                                    0, food_n, it, drink_n, it1, dessert_n,
                                    it2, it3
                                )
                            }
                        }
                    }
                }
                order?.let { orderViewModel.insert(it) }
                view.findNavController().navigate(R.id.action_order_to_menu)
            }


        }

        binding.orderViewModel = orderViewModel

        return binding.root
    }
}