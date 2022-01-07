package com.example.kfeapp.food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kfeapp.R
import com.example.kfeapp.SharedViewModel
import com.example.kfeapp.databinding.FragmentDrinkBinding
import com.example.kfeapp.db.drink.Drink


class Adapter(
    private val context: Context,
    private val list: ArrayList<FoodModel>,
    private val vm: SharedViewModel
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(view: View, vm: SharedViewModel) : RecyclerView.ViewHolder(view) {
        val price: TextView = view.findViewById(R.id.tv_price)
        val name: TextView = view.findViewById(R.id.tv_name)
        val description: TextView = view.findViewById(R.id.tv_desc)

        init {
            itemView.setOnClickListener { view: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Item added: ${name.text} - ${price.text}", Toast.LENGTH_SHORT).show()
                vm.saveFood(name.text.toString(), price.text.toString())
                view.findNavController().navigate(R.id.action_food_to_dessert)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view, vm)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.price.text = data.price.toString() + "€"
        holder.name.text = data.name
        holder.description.text = data.description
    }


}