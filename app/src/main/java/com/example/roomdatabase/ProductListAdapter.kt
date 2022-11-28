package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.room.Product

class ProductListAdapter : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.energyValue)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private val productEnergyValue: TextView = itemView.findViewById(R.id.energy_value)

        fun bind(name: String?, energyValue: Int?) {
            productName.text = name
            productEnergyValue.text = itemView.context.getString(R.string.energy_value_template, energyValue)
        }

        companion object {
            fun create(parent: ViewGroup): ProductViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ProductViewHolder(view)
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }
    }
}