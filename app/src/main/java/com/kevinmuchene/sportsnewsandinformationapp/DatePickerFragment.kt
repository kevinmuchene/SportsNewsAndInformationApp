package com.kevinmuchene.sportsnewsandinformationapp

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DatePickerFragment(private val onDateSelected: (String) -> Unit) : DialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showDatePicker()
    }

    private fun showDatePicker() {

        val datePickerBuilder = MaterialDatePicker.Builder.datePicker()

        datePickerBuilder.setTitleText("Select date")

        val datePicker = datePickerBuilder.build()

        datePicker.addOnPositiveButtonClickListener { selection: Long ->

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
            onDateSelected(formattedDate)
        }

        datePicker.show(parentFragmentManager, datePicker.toString())
    }
}


