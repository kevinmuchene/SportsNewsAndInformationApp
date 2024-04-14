package com.kevinmuchene.sportsnewsandinformationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.sportsnewsandinformationapp.data.HistoricalSportsData

class HistoricalSportsCustomAdapter(var historicalSportsData: ArrayList<HistoricalSportsData>): RecyclerView.Adapter<HistoricalSportsCustomAdapter.HistoricalSportsAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoricalSportsCustomAdapter.HistoricalSportsAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.historical_sports_activity, parent, false)

        return HistoricalSportsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HistoricalSportsCustomAdapter.HistoricalSportsAdapterViewHolder,
        position: Int
    ) {

        val sportsData = historicalSportsData[position]

        holder.historyName.text = sportsData.historyName
        holder.historyDate.text = sportsData.historyDate
        holder.historyDescription.text = sportsData.historyDescription
    }

    override fun getItemCount(): Int {

        return historicalSportsData.size;
    }

    inner class HistoricalSportsAdapterViewHolder(view : View): RecyclerView.ViewHolder(view) {

        val historyName : TextView
        val historyDate : TextView
        val historyDescription: TextView

        init {
            historyName = view.findViewById(R.id.historyNameId)
            historyDate = view.findViewById(R.id.historyDateId)
            historyDescription = view.findViewById(R.id.historyDescriptionId)
        }
    }
}