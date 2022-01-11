package com.example.kfeapp.history

import android.content.Context
import android.util.Log
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
import com.example.kfeapp.db.drink.Drink


class Adapter(
    private val context: Context,
    private val list: ArrayList<HistoryModel>,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderId: TextView = view.findViewById(R.id.tv_order_id)
        val food_n: TextView = view.findViewById(R.id.tv_order_food_n)
        val food_p: TextView = view.findViewById(R.id.tv_order_food_p)
        val drink_n: TextView = view.findViewById(R.id.tv_order_drink_n)
        val drink_p: TextView = view.findViewById(R.id.tv_order_drink_p)
        val dessert_n: TextView = view.findViewById(R.id.tv_order_dessert_n)
        val dessert_p: TextView = view.findViewById(R.id.tv_order_dessert_p)
        val total: TextView = view.findViewById(R.id.tv_order_total_p)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.orderId.text = "Order nº" + data.orderId.toString()
        holder.food_n.text = data.food_n
        holder.food_p.text = data.food_p.toString() + "€"
        holder.drink_n.text = data.drink_n
        holder.drink_p.text = data.drink_p.toString() + "€"
        holder.dessert_n.text = data.dessert_n
        holder.dessert_p.text = data.dessert_p.toString() + "€"
        holder.total.text = data.total.toString() + "€"
    }


}