package com.kel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kel3.tabsy.Restaurant
import com.kel3.tabsy2nd.R
import com.kel3.tabsy2nd.RestaurantViewActivity

class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant: Restaurant = restaurantList[position]
        holder.tvName.text = restaurant.name
        holder.tvDescription.text = restaurant.description

        // Menangani klik pada item
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RestaurantViewActivity::class.java)
            intent.putExtra("restaurant_name", restaurant.name)
            intent.putExtra("restaurant_description", restaurant.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        var tvDescription: TextView = itemView.findViewById(R.id.tvRestaurantDescription)
    }
}