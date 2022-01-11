package com.example.kfeapp.dessert

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
import com.example.kfeapp.databinding.FragmentDessertBinding
import com.example.kfeapp.databinding.FragmentFoodBinding
import com.example.kfeapp.db.KFeDB
import com.example.kfeapp.db.dessert.Dessert
import com.example.kfeapp.food.Adapter
import com.example.kfeapp.food.FoodModel
import com.example.kfeapp.food.FoodViewModel
import com.example.kfeapp.food.FoodViewModelFactory

class DessertFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDessertBinding>(
            inflater,
            R.layout.fragment_dessert, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = KFeDB.getInstance(application).dessertDao
        val viewModelFactory = DessertViewModelFactory(dataSource, application)
        val dessertViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DessertViewModel::class.java)

        binding.apply {
            rvDessert.layoutManager = LinearLayoutManager(context)
            rvDessert.adapter = context?.let { Adapter(it, fetchDesserts(dessertViewModel), sharedViewModel) }
            rvDessert.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        //INSERTS
/*
        dessertViewModel.insert(Dessert(0, "Flan", "Default description, don't want to write", 2.00))
        dessertViewModel.insert(Dessert(0, "Ice Cream", "Default description, don't want to write", 1.50))
        dessertViewModel.insert(Dessert(0, "Apple Pie", "Default description, don't want to write", 1.20))
        dessertViewModel.insert(Dessert(0, "Choco Pie", "Default description, don't want to write", 1.50))
        dessertViewModel.insert(Dessert(0, "Haggis", "Default description, don't want to write", 3.50))
        dessertViewModel.insert(Dessert(0, "Vodka Shot", "Default description, don't want to write", 1.00))
        dessertViewModel.insert(Dessert(0, "Jagger Shot", "Default description, don't want to write", 1.00))
        dessertViewModel.insert(Dessert(0, "JB Shot", "Default description, don't want to write", 1.00))
*/

        binding.lifecycleOwner = this
        binding.dessertViewModel = dessertViewModel

        return binding.root
    }

    private fun fetchDesserts(vm: DessertViewModel): ArrayList<DessertModel> {
        val list = arrayListOf<DessertModel>()
        val desserts = vm.getAllDesserts()

        for (id in 1..desserts.size - 1) {
            val price = desserts.get(id).dessertPrice
            val name = desserts.get(id).dessertName
            val desc = desserts.get(id).dessertDescription

            val dessertModel = DessertModel(
                price,
                name,
                desc
            )
            list.add(dessertModel)
        }
        Log.d("DRINKS", "JELOU")
        return list
    }

}