package com.kel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kel3.tabsy.Restaurant
import com.kel3.tabsy2nd.R


class RestaurantAdapter(restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    private val restaurantList: List<Restaurant> = restaurantList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant: Restaurant = restaurantList[position]
        holder.tvName.text = restaurant.name // Accessing the property directly
        holder.tvDescription.text = restaurant.description // Accessing the property directly
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById<TextView>(R.id.tvRestaurantName)
        var tvDescription: TextView = itemView.findViewById<TextView>(R.id.tvRestaurantDescription)
    }
}
