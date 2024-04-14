package com.kevinmuchene.sportsnewsandinformationapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.sportsnewsandinformationapp.data.SportsData
import com.kevinmuchene.sportsnewsandinformationapp.databinding.FragmentSportsBinding

class SportsFragment() : Fragment() {

    private lateinit var binding: FragmentSportsBinding;

    private lateinit var sportsCustomAdapter: SportsCustomAdapter

    private lateinit var sportsData: ArrayList<SportsData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSportsBinding.inflate(inflater, container, false)

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sportsData = ArrayList()

        sportsData.add(SportsData("Measure", "Car Racing",
            """Car racing, a high-speed motorsport, involves competitors racing against each other in specialized vehicles on various tracks. The sport measures precision, speed, and technical skill, with popular forms including Formula One, NASCAR, and rally racing. It emphasizes advanced engineering and aerodynamics.""".trimIndent()
        ))

        sportsData.add(SportsData("Precision", "Golf",
            """Golf, a precision sport, entails players using clubs to hit a small ball into a series of holes on a course with the fewest strokes possible. It emphasizes accuracy, strategy, and mental focus, requiring players to navigate diverse terrains and weather conditions while maintaining composure and technique.""".trimIndent()
        ))

        sportsData.add(SportsData("Spectacle", "Gymnastic",
            """Gymnastics, a spectacle sport, showcases athletes performing intricate routines that combine strength, flexibility, balance, and coordination. It features events like floor exercises, beam, bars, and vault, captivating audiences with breathtaking acrobatics and precise movements. The sport demands rigorous training and exceptional discipline, offering a visually stunning display of human physical capabilities.""".trimIndent()
        ))
        sportsData.add(SportsData("Spectacle", "Skateboarding",
            """Skateboarding, categorized as a spectacle sport, captivates audiences with daring tricks and high-flying stunts on ramps, rails, and in street settings. Skaters perform a variety of maneuvers like ollies, flips, and grinds, showcasing creativity and technical skill. The sport thrives on individual style and innovation, making each performance unique and engaging""".trimIndent()
        ))

        sportsCustomAdapter = SportsCustomAdapter(sportsData)

        binding.sportsAdapterId.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.sportsAdapterId.adapter = sportsCustomAdapter

        binding.sportsFabId.setOnClickListener {
            displayAddSportDialog()
        }

    }

    private fun displayAddSportDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_sport, null)

        val spinner: Spinner = dialogView.findViewById(R.id.sportTypeSpinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sports_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btnAddSport).setOnClickListener {
            val sportType = spinner.selectedItem.toString()
            val sportName = dialogView.findViewById<EditText>(R.id.etSportName).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.etSportDescription).text.toString()

            if ( sportType.isNotEmpty() && sportName.isNotEmpty() && description.isNotEmpty()) {
                val newSport = SportsData(sportType, sportName, description)
                sportsData.add(newSport)
                sportsCustomAdapter.notifyItemInserted(sportsData.size - 1)
                dialog.dismiss()
            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}