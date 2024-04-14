package com.kevinmuchene.sportsnewsandinformationapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kevinmuchene.sportsnewsandinformationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val myCustomAdapter = MyCustomAdapter(this);

        binding.viewPager2Id.adapter = myCustomAdapter
        binding.viewPager2Id.offscreenPageLimit = 6

        binding.tabLayoutId.tabGravity = TabLayout.GRAVITY_FILL
        binding.tabLayoutId.tabMode = TabLayout.MODE_SCROLLABLE

        TabLayoutMediator(binding.tabLayoutId, binding.viewPager2Id) { tab, position ->

            when(position) {
                0 -> {
                    tab.setText("Sports")
                    tab.setIcon(R.drawable.sports)
                }
                1 -> {
                    tab.setText("News")
                    tab.setIcon(R.drawable.baseline_newspaper_24)
                }
                2 -> {
                    tab.setText("Athletes")
                    tab.setIcon(R.drawable.baseline_sports_kabaddi_24)
                }
                3 -> {
                    tab.setText("Events")
                    tab.setIcon(R.drawable.baseline_event_24)
                }
                4 -> {
                    tab.setText("H. Archives")
                    tab.setIcon(R.drawable.baseline_history_24)
                }
                5 -> {
                    tab.setText("About Me")
                    tab.setIcon(R.drawable.baseline_person_24)
                }
            }

        }.attach()


        binding.btnNavigationId.setOnItemSelectedListener {

           val position = when(it.itemId) {
                R.id.newsMenuId -> 1
                R.id.eventsMenuId -> 3
                R.id.historicalArchivesMenuId -> 4
               else -> 0
            }
            binding.viewPager2Id.currentItem = position
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}