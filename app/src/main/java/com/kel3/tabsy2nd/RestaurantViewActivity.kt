package com.kel3.tabsy2nd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RestaurantViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_view)

        // Ambil data dari Intent
        val restaurantName = intent.getStringExtra("restaurant_name")
        val restaurantDescription = intent.getStringExtra("restaurant_description")

        // Set data ke dalam View
        val nameTextView = findViewById<TextView>(R.id.tvRestaurantName)
        val descriptionTextView = findViewById<TextView>(R.id.tvRestaurantDescription)
        val bookTableButton = findViewById<Button>(R.id.btnBookTable)

        nameTextView.text = restaurantName
        descriptionTextView.text = restaurantDescription

        // Tombol untuk memesan meja
        bookTableButton.setOnClickListener {
            // Navigate to ReservasiFragment
            val reservasiFragment = ReservasiFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, reservasiFragment) // Use the ID of your container
                .addToBackStack(null) // Optional: add to back stack to allow back navigation
                .commit()
        }
    }
}
