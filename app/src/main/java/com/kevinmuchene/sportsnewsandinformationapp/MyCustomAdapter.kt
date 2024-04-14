package com.kevinmuchene.sportsnewsandinformationapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyCustomAdapter( fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
//        TODO("Not yet implemented")

        return 6;
    }

    override fun createFragment(position: Int): Fragment {
//        TODO("Not yet implemented")

        return when(position) {
            0 -> SportsFragment()
            1 -> NewsFragment()
            2 -> AthletesFragment()
            3 -> EventsFragment()
            4 -> HistoricalSportsArchiveFragment()
            5 -> AboutMeFragment()
            else -> Fragment();
        }
    }
}