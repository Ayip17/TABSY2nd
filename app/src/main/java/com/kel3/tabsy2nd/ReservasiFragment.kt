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

    private var countDewasa = 0
    private var countAnak = 0
    private var countBalita = 0

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Date Picker
        val btnDatePicker: Button = view.findViewById(R.id.btnDatePicker)
        val tvSelectedDate: TextView = view.findViewById(R.id.tvSelectedDate)
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

        // Time Picker
        val btnTimePicker: Button = view.findViewById(R.id.btnTimePicker)
        val tvSelectedTime: TextView = view.findViewById(R.id.tvSelectedTime)
        btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvSelectedTime.text = "Waktu: $formattedTime"
            }, hour, minute, true).show()
        }

        //Counter
        val tvTotalPelanggan: TextView = view.findViewById(R.id.tvTotalPelanggan)

        // Dewasa
        val btnMinusDewasa: Button = view.findViewById(R.id.btnMinusDewasa)
        val btnPlusDewasa: Button = view.findViewById(R.id.btnPlusDewasa)
        val tvCountDewasa: TextView = view.findViewById(R.id.tvCountDewasa)

        btnMinusDewasa.setOnClickListener {
            if (countDewasa > 0) {
                countDewasa--
                tvCountDewasa.text = countDewasa.toString()
                updateTotal(tvTotalPelanggan)
            }
        }

        btnPlusDewasa.setOnClickListener {
            countDewasa++
            tvCountDewasa.text = countDewasa.toString()
            updateTotal(tvTotalPelanggan)
        }

        // Anak-anak
        val btnMinusAnak: Button = view.findViewById(R.id.btnMinusAnak)
        val btnPlusAnak: Button = view.findViewById(R.id.btnPlusAnak)
        val tvCountAnak: TextView = view.findViewById(R.id.tvCountAnak)

        btnMinusAnak.setOnClickListener {
            if (countAnak > 0) {
                countAnak--
                tvCountAnak.text = countAnak.toString()
                updateTotal(tvTotalPelanggan)
            }
        }

        btnPlusAnak.setOnClickListener {
            countAnak++
            tvCountAnak.text = countAnak.toString()
            updateTotal(tvTotalPelanggan)
        }

        // Balita
        val btnMinusBalita: Button = view.findViewById(R.id.btnMinusBalita)
        val btnPlusBalita: Button = view.findViewById(R.id.btnPlusBalita)
        val tvCountBalita: TextView = view.findViewById(R.id.tvCountBalita)

        btnMinusBalita.setOnClickListener {
            if (countBalita > 0) {
                countBalita--
                tvCountBalita.text = countBalita.toString()
                updateTotal(tvTotalPelanggan)
            }
        }

        btnPlusBalita.setOnClickListener {
            countBalita++
            tvCountBalita.text = countBalita.toString()
            updateTotal(tvTotalPelanggan)
        }
    }

    private fun updateTotal(totalView: TextView) {
        val total = countDewasa + countAnak + countBalita
        totalView.text = "Total Pelanggan: $total"
    }
}