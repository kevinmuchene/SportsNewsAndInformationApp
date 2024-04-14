package com.kevinmuchene.sportsnewsandinformationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.sportsnewsandinformationapp.data.AthletesData

class AthletesCustomAdapter(var athletesData: ArrayList<AthletesData>): RecyclerView.Adapter<AthletesCustomAdapter.AthletesAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthletesCustomAdapter.AthletesAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.athletes_activity_details, parent, false)

        return AthletesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AthletesCustomAdapter.AthletesAdapterViewHolder, position: Int) {

        val athlete = athletesData[position]

        holder.athletesName.text = athlete.athletesName
        holder.sportName.text = athlete.sportName
        holder.countryName.text = athlete.countryName
        holder.bestPerformance.text = athlete.bestPerformanceName
        holder.medals.text = athlete.medals
        holder.facts.text = athlete.facts

    }

    override fun getItemCount(): Int {
       return athletesData.size
    }

    inner class AthletesAdapterViewHolder(view : View): RecyclerView.ViewHolder(view) {

        val athletesName: TextView
        val sportName: TextView
        val countryName: TextView
        val bestPerformance: TextView
        val medals: TextView
        val facts: TextView

        init {
            athletesName = view.findViewById(R.id.athleteNameId)
            sportName = view.findViewById(R.id.sportNameId)
            countryName = view.findViewById(R.id.countryId)
            bestPerformance = view.findViewById(R.id.bestPerformanceId)
            medals = view.findViewById(R.id.medalsId)
            facts = view.findViewById(R.id.factsId)
        }

    }
}