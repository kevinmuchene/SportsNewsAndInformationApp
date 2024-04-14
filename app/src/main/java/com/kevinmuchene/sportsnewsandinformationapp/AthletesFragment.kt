package com.kevinmuchene.sportsnewsandinformationapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.sportsnewsandinformationapp.data.AthletesData
import com.kevinmuchene.sportsnewsandinformationapp.databinding.AthletesActivityDetailsBinding
import com.kevinmuchene.sportsnewsandinformationapp.databinding.FragmentAthletesBinding

class AthletesFragment : Fragment() {

    private lateinit var binding: FragmentAthletesBinding;

    private lateinit var athleteCustomAdapter : AthletesCustomAdapter

    private lateinit var athletesData: ArrayList<AthletesData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentAthletesBinding.inflate(inflater, container, false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        athletesData = ArrayList();

        athletesData.add(AthletesData("Michael Jordan", "BasketBall", "United States Of America", "New York Opens", "V.I.P", "He is the best player in the world"))
        athletesData.add(AthletesData("Lionel Messi", "Football", "Argentina", "Champions League", "Champions League", "He is the best player in the world"))
        athletesData.add(AthletesData("Victor Wanyama", "Football", "Kenya", "E.P.L", "England Champions", "He is the best player in the world"))
        athletesData.add(AthletesData("Selena Williams", "Tennis", "United States Of America", "Florida Opens", "Tennis Opens", "He is the best player in the world"))

        athleteCustomAdapter = AthletesCustomAdapter(athletesData)

        binding.athleteRecyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        binding.athleteRecyclerViewId.adapter = athleteCustomAdapter

        binding.athleteFabId.setOnClickListener {
            displayAddAthletesDialog()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun displayAddAthletesDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_athletes, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()


        dialogView.findViewById<Button>(R.id.btnAddAthletes).setOnClickListener {
            val athleteName = dialogView.findViewById<EditText>(R.id.diagAthleteName).text.toString()
            val sportName = dialogView.findViewById<EditText>(R.id.diagAthleteSportName).text.toString()
            val country = dialogView.findViewById<EditText>(R.id.diagAthleteCountry).text.toString()
            val bestPerformance = dialogView.findViewById<EditText>(R.id.diagAthleteBestPerformance).text.toString()
            val medals = dialogView.findViewById<EditText>(R.id.diagAthleteMedals).text.toString()
            val facts = dialogView.findViewById<EditText>(R.id.diagAthleteFacts).text.toString()

            if(athleteName.isNotEmpty() && sportName.isNotEmpty() && country.isNotEmpty()
                && bestPerformance.isNotEmpty() && medals.isNotEmpty() && facts.isNotEmpty()){
                val athlete = AthletesData(athleteName, sportName, country, bestPerformance, medals,facts)
                athletesData.add(athlete)
                athleteCustomAdapter.notifyItemInserted(athletesData.size - 1)
                dialog.dismiss()
            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}