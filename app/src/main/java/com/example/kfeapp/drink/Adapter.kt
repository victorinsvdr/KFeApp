package com.example.kfeapp.drink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kfeapp.R
import com.example.kfeapp.databinding.FragmentDrinkBinding
import com.example.kfeapp.db.drink.Drink


class Adapter(
    private val context: Context,
    private val list: ArrayList<DrinkModel>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView = view.findViewById(R.id.tv_price)
        val name: TextView = view.findViewById(R.id.tv_name)
        val description: TextView = view.findViewById(R.id.tv_desc)

        init {
            itemView.setOnClickListener { view: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Item added: ${name.text}: ${price.text}", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
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

interface onDrinkItemClickListener {
    fun onItemClick(item: Drink, position: Int)
}
