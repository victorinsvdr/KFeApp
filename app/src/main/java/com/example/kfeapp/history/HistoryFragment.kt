package com.example.kfeapp.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kfeapp.R
import com.example.kfeapp.databinding.FragmentHistoryBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.drink.DrinkViewModel
import com.example.kfeapp.drink.DrinkViewModelFactory
import com.example.kfeapp.food.Adapter
import com.example.kfeapp.food.FoodModel
import com.example.kfeapp.food.FoodViewModel
import com.example.kfeapp.order.OrderViewModel
import com.example.kfeapp.order.OrderViewModelFactory

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(
            inflater,
            R.layout.fragment_history, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).orderDao
        val viewModelFactory = OrderViewModelFactory(dataSource, application)
        val orderViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(OrderViewModel::class.java)

        binding.apply {
            rvHistory.layoutManager = LinearLayoutManager(context)
            rvHistory.adapter = context?.let { Adapter(it, fetchOrders(orderViewModel)) }
            //rvHistory.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        return binding.root

    }

    private fun fetchOrders(vm: OrderViewModel): ArrayList<HistoryModel> {
        val list = arrayListOf<HistoryModel>()
        val orders = vm.getAllOrders()
        Log.d("ORDERS", "${orders.size}")

        for (id in 0..orders.size - 1) {
            val food_n = orders.get(id).foodName
            val food_p = orders.get(id).foodPrice
            val drink_n = orders.get(id).drinkName
            val drink_p = orders.get(id).drinkPrice
            val dessert_n = orders.get(id).dessertName
            val dessert_p = orders.get(id).dessertPrice
            val total = orders.get(id).total
            val orderId = orders.get(id).orderId

            val historyModel = HistoryModel(
                food_n, food_p, drink_n, drink_p, dessert_n, dessert_p, total, orderId
            )
            Log.d("ORDERS", "$food_n, $food_p")
            list.add(historyModel)
        }

        return list
    }
}