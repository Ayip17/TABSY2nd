package com.kel3.tabsy2nd

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class ReservasiFragment : Fragment(R.layout.fragment_reservasi) {

    @SuppressLint("SetTextI18n")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referensi ke views
        val btnDatePicker: Button = view.findViewById(R.id.btnDatePicker)
        val tvSelectedDate: TextView = view.findViewById(R.id.tvSelectedDate)
        val btnTimePicker: Button = view.findViewById(R.id.btnTimePicker)
        val tvSelectedTime: TextView = view.findViewById(R.id.tvSelectedTime)
        val spinnerOptions: Spinner = view.findViewById(R.id.spinnerOptions)
        val tvSelectedOption: TextView = view.findViewById(R.id.tvSelectedOption)


        // Tombol DatePicker
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = "Tanggal: $formattedDate"
            }, year, month, day).show()
        }

        // Tombol TimePicker
        btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvSelectedTime.text = "Waktu: $formattedTime"
            }, hour, minute, true).show()
        }

        // Spinner Dropdown Menu
        val options = listOf("Opsi 1", "Opsi 2", "Opsi 3")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOptions.adapter = adapter

        spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = options[position]
                tvSelectedOption.text = "Pilihan: $selectedOption"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
