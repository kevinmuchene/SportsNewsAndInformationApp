package com.kevinmuchene.sportsnewsandinformationapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
//import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.sportsnewsandinformationapp.data.EventsData
import com.kevinmuchene.sportsnewsandinformationapp.databinding.FragmentEventsBinding
import java.util.Calendar

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding

    private lateinit var eventsData: ArrayList<EventsData>

    private lateinit var eventsCustomAdapter: EventsCustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventsBinding.inflate(inflater, container, false)

        return binding.root;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsData = ArrayList<EventsData>()

        eventsData.add(EventsData("FIBA BasketBall World Cup Qualifiers", "10/03/2024", "Qualify games for the 2025 FIBA Basketball World Cup"))
        eventsData.add(EventsData("Indian Primer League(IPL)", "15/03/2024", "Twenty20 Cricket league in India"))

        eventsCustomAdapter = EventsCustomAdapter(eventsData)

        binding.eventsRecyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        binding.eventsRecyclerViewId.adapter = eventsCustomAdapter

        binding.eventsFabId.setOnClickListener {
            displayAddEventDialog()
        }
    }

    private fun displayAddEventDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_events, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        val dateEditText = dialogView.findViewById<EditText>(R.id.diagEventDate)

        dateEditText.setOnClickListener {
            val datePickerFragment = DatePickerFragment { selectedDate ->
                dateEditText.setText(selectedDate)
            }
            datePickerFragment.show(parentFragmentManager, "datePicker")
        }

        dialogView.findViewById<Button>(R.id.btnAddEvent).setOnClickListener {
            val eventName = dialogView.findViewById<EditText>(R.id.diagEventName).text.toString()
            val eventDescription = dialogView.findViewById<EditText>(R.id.diagEventDescription).text.toString()
//            val eventDate = dialogView.findViewById<EditText>(R.id.diagEventDate).text.toString()
            val eventDate = dateEditText.text.toString()
            if(eventName.isNotEmpty() && eventDescription.isNotEmpty() && eventDate.isNotEmpty()) {
                val newEvent = EventsData(eventName, eventDate, eventDescription)
                eventsData.add(newEvent)
                eventsCustomAdapter.notifyItemInserted(eventsData.size - 1)
                dialog.dismiss()
            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
