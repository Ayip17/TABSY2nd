package com.kel3.tabsy2nd

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kel.RestaurantAdapter
import com.kel3.tabsy.Restaurant
import java.util.*

class BerandaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_beranda
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        // Inisialisasi Spinner
        val spinnerPeople = view.findViewById<Spinner>(R.id.people_count)

        // Isi Spinner dengan data
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.people_count_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPeople.adapter = adapter

        // Inisialisasi Button untuk memilih tanggal
        val btnDate = view.findViewById<Button>(R.id.date_button)
        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    btnDate.text = selectedDate
                },
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            )
            datePicker.show()
        }

        // Inisialisasi Button untuk memilih waktu
        val btnTime = view.findViewById<Button>(R.id.time_button)
        btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    btnTime.text = selectedTime
                },
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE],
                true
            )
            timePicker.show()
        }

        // Inisialisasi RecyclerView
        val recyclerViewRec = view.findViewById<RecyclerView>(R.id.recommended_restaurants)
        val recyclerViewNear = view.findViewById<RecyclerView>(R.id.nearest_restaurants)
        recyclerViewRec.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNear.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)



        // Data untuk RecyclerView Recommended
        val recommendedRestaurants: MutableList<Restaurant> = ArrayList()
        recommendedRestaurants.add(Restaurant("Restoran A", "Deskripsi Restoran A"))
        recommendedRestaurants.add(Restaurant("Restoran B", "Deskripsi Restoran B"))
        recommendedRestaurants.add(Restaurant("Restoran C", "Deskripsi Restoran C"))
        recommendedRestaurants.add(Restaurant("Restoran D", "Deskripsi Restoran D"))

        // Data untuk RecyclerView Nearest
        val nearestRestaurants: MutableList<Restaurant> = ArrayList()
        nearestRestaurants.add(Restaurant("Restoran W", "Dekat Lokasi Anda"))
        nearestRestaurants.add(Restaurant("Restoran X", "Dekat Lokasi Anda"))
        nearestRestaurants.add(Restaurant("Restoran Y", "Dekat Lokasi Anda"))
        nearestRestaurants.add(Restaurant("Restoran Z", "Dekat Lokasi Anda"))

        // Set Adapter untuk Recommended
        val recommendAdapter = RestaurantAdapter(recommendedRestaurants)
        recyclerViewRec.adapter = recommendAdapter

        // Set Adapter untuk Nearest
        val nearestAdapter = RestaurantAdapter(nearestRestaurants)
        recyclerViewNear.adapter = nearestAdapter

        // Tombol Search dengan Validasi
        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            val selectedPeople = spinnerPeople.selectedItem.toString()
            val selectedDate = btnDate.text.toString()
            val selectedTime = btnTime.text.toString()

            if (selectedPeople.isEmpty() || selectedDate == "Pilih Tanggal" || selectedTime == "Pilih Waktu") {
                Toast.makeText(requireContext(), "Harap lengkapi semua input", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Mencari restoran untuk $selectedPeople pada $selectedDate pukul $selectedTime",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Return View di akhir metode
        return view
    }
}
