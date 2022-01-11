package com.example.kfeapp.drink

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentDrinkBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.drink.Drink


class DrinkFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDrinkBinding>(
            inflater,
            R.layout.fragment_drink, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).drinkDao
        val viewModelFactory = DrinkViewModelFactory(dataSource, application)
        val drinkViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DrinkViewModel::class.java)

        binding.apply {
            rvDrink.layoutManager = LinearLayoutManager(context)
            rvDrink.adapter = context?.let { Adapter(it, fetchDrinks(drinkViewModel), sharedViewModel) }
            rvDrink.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }




        // INSERTS
/*
        drinkViewModel.insert(Drink(0, "Machiatto", "A machiatto coffee", 0.90))
        drinkViewModel.insert(Drink(0, "Ristretto", "A strong short sized coffee", 1.00))
        drinkViewModel.insert(Drink(0, "Ice Coffee", "Coffee with shards of ice", 1.20))
        drinkViewModel.insert(Drink(0, "Amstel", "0,66L of Amstel beer", 1.40))
        drinkViewModel.insert(Drink(0, "San Miguel", "0,66L of San Miguel beer", 1.50))
        drinkViewModel.insert(Drink(0, "Franziskaner", "0,66L of Franziskaner imported beer", 2.00))
        drinkViewModel.insert(Drink(0, "Paulaner", "0,66L of Paulaner imported beer", 2.00))
        drinkViewModel.insert(Drink(0, "Coca-cola", "0,33L of canned Coca-cola", 0.40))
        drinkViewModel.insert(Drink(0, "Pepsi", "0,33L of Pepsi", 0.40))
        drinkViewModel.insert(Drink(0, "Coca-cola Zero", "0,66L of Coca-cola Zero", 0.45))
*/


        binding.lifecycleOwner = this
        binding.drinkViewModel = drinkViewModel

        return binding.root
    }

    private fun fetchDrinks(vm: DrinkViewModel): ArrayList<DrinkModel> {
        val list = arrayListOf<DrinkModel>()
        val drinks = vm.getAllDrinks()

        for (id in 1..drinks.size - 1) {
            val price = drinks.get(id).drinkPrice
            val name = drinks.get(id).drinkName
            val desc = drinks.get(id).drinkDescription

            val drinkModel = DrinkModel(
                price,
                name,
                desc
            )
            list.add(drinkModel)
        }
        Log.d("DRINKS", "JELOU")
        return list
    }
}
