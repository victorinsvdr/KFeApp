package com.example.kfeapp.food

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentFoodBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.food.Food
import com.example.kfeapp.drink.Adapter
import com.example.kfeapp.drink.DrinkModel
import com.example.kfeapp.drink.DrinkViewModel
import com.example.kfeapp.food.FoodViewModel
import com.example.kfeapp.food.FoodViewModelFactory

class FoodFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFoodBinding>(
            inflater,
            R.layout.fragment_food, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).foodDao
        val viewModelFactory = FoodViewModelFactory(dataSource, application)
        val foodViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(FoodViewModel::class.java)

        binding.apply {
            rvFood.layoutManager = LinearLayoutManager(context)
            rvFood.adapter = context?.let { Adapter(it, fetchFoods(foodViewModel), sharedViewModel) }
            rvFood.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }


        // INSERTS
/*
        foodViewModel.insert(Food(0, "Chorizo Sandwitch", "Delicious Chorizo from Burgos", 2.70))
        foodViewModel.insert(Food(0, "Tortilla Sandwitch", "Best Tortilla you will ever try", 3.00))
        foodViewModel.insert(Food(0, "Fuet Sandwitch", "Default description, I don't want to write", 3.20))
        foodViewModel.insert(Food(0, "Salami Sandiwtch", "Default description, I don't want to write", 3.40))
        foodViewModel.insert(Food(0, "Cheese & Salad Sandwitch", "Default description, I don't want to write", 3.50))
        foodViewModel.insert(Food(0, "Tuna Sandwitch", "Default description, I don't want to write", 3.5))
        foodViewModel.insert(Food(0, "Jamon Sandwitch ", "Default description, I don't want to write", 2.00))
        foodViewModel.insert(Food(0, "Pepper Sandwitch", "Default description, I don't want to write", 2.40))
        foodViewModel.insert(Food(0, "Jamon y Queso Sandwitch", "Default description, I don't want to write", 2.40))
        foodViewModel.insert(Food(0, "Morcilla Sandwitch", "Default description, I don't want to write", 3.45))
*/
        //Toast.makeText(context, "${sharedViewModel.drink.value} PRICE: ${sharedViewModel.drinkPrice.value}" , Toast.LENGTH_LONG).show()

        binding.lifecycleOwner = this
        binding.foodViewModel = foodViewModel

        return binding.root
    }

    private fun fetchFoods(vm: FoodViewModel): ArrayList<FoodModel> {
        val list = arrayListOf<FoodModel>()
        val foods = vm.getAllFoods()

        for (id in 1..foods.size - 1) {
            val price = foods.get(id).foodPrice
            val name = foods.get(id).foodName
            val desc = foods.get(id).foodDescription

            val foodModel = FoodModel(
                price,
                name,
                desc
            )
            list.add(foodModel)
        }
        Log.d("DRINKS", "JELOU")
        return list
    }

}