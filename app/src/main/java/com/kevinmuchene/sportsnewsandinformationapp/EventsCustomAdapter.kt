package com.kevinmuchene.sportsnewsandinformationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.sportsnewsandinformationapp.data.EventsData

class EventsCustomAdapter(var eventsData: ArrayList<EventsData>): RecyclerView.Adapter<EventsCustomAdapter.EventsAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsCustomAdapter.EventsAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_activity_details, parent, false)

        return EventsAdapterViewHolder(view);
    }

    override fun onBindViewHolder(
        holder: EventsCustomAdapter.EventsAdapterViewHolder,
        position: Int
    ) {

        val event = eventsData[position]

        holder.eventName.text = event.eventName
        holder.eventDate.text = event.eventDate
        holder.eventDescription.text = event.eventDescription

    }

    override fun getItemCount(): Int {
        return eventsData.size
    }

    inner class EventsAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val eventName: TextView
        val eventDate : TextView
        val eventDescription : TextView

        init {
            eventName = view.findViewById(R.id.eventNameId)
            eventDate = view.findViewById(R.id.eventDateId)
            eventDescription = view.findViewById(R.id.eventDescriptionId)
        }
    }
}