package com.kevinmuchene.sportsnewsandinformationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.sportsnewsandinformationapp.data.SportsData

class SportsCustomAdapter(var sportsData: ArrayList<SportsData>): RecyclerView.Adapter<SportsCustomAdapter.SportsAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.sports_activity_details, parent, false)

        return SportsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportsAdapterViewHolder, position: Int) {

        val sportData = sportsData[position]

        holder.sportsType.text = sportData.sportsType
        holder.sportsName.text = sportData.sportsName
        holder.sportsInstructions.text = sportData.instruction


    }

    override fun getItemCount(): Int {

        return sportsData.size;
    }

    inner class SportsAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val sportsType : TextView
        val sportsName : TextView
        val sportsInstructions : TextView

        init {
            sportsType = view.findViewById(R.id.sportsTypeId)
            sportsName = view.findViewById(R.id.sportsNameId)
            sportsInstructions = view.findViewById(R.id.sportsInstructionId)
        }

    }
}