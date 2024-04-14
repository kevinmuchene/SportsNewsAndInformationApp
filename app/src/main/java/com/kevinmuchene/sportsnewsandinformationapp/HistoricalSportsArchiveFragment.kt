package com.kevinmuchene.sportsnewsandinformationapp

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
import com.kevinmuchene.sportsnewsandinformationapp.data.EventsData
import com.kevinmuchene.sportsnewsandinformationapp.data.HistoricalSportsData
import com.kevinmuchene.sportsnewsandinformationapp.databinding.FragmentHistoricalSportsArchiveBinding

class HistoricalSportsArchiveFragment : Fragment() {

    private lateinit var binding: FragmentHistoricalSportsArchiveBinding
    private lateinit var historicalSportsData: ArrayList<HistoricalSportsData>
    private lateinit var historicalSportsCustomAdapter : HistoricalSportsCustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoricalSportsArchiveBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historicalSportsData = ArrayList<HistoricalSportsData>()

        historicalSportsData.add(
            HistoricalSportsData("First Olympic Games", "06/04/1896", """The first modern Olympic Games were held in Athens, Greece, in 1896. Revived by Pierre de Coubertin, they featured 14 nations and 241 athletes competing in 43 events across 9 sports. This inaugural event marked the rebirth of the ancient Olympic tradition, fostering international sportsmanship and unity.""".trimIndent())
        )
        historicalSportsData.add(
            HistoricalSportsData("Jessee Owens at the 1936 Olympic Games", "08/03/1936", """Jesse Owens famously triumphed at the 1936 Berlin Olympics, winning four gold medals in track and field. His victories in the 100m, 200m, long jump, and 4x100m relay not only defied Hitler's theories of racial superiority but also cemented his legacy as one of the greatest athletes in history""".trimIndent())
        )

        historicalSportsCustomAdapter = HistoricalSportsCustomAdapter(historicalSportsData)

        binding.historicalSportsRecyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        binding.historicalSportsRecyclerViewId.adapter = historicalSportsCustomAdapter

        binding.historicalSportsArchiveFabId.setOnClickListener {
            displayAddHistoryDialog()
        }
    }

    private fun displayAddHistoryDialog(){
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.diag_historical_sports_archive, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        val historyDateEditText = dialogView.findViewById<EditText>(R.id.diagHistoryDate)

        historyDateEditText.setOnClickListener {
            val datePickerFragment = DatePickerFragment{selectedDate ->
                historyDateEditText.setText(selectedDate)
            }
            datePickerFragment.show(parentFragmentManager, "historyDatePicker")
        }

        dialogView.findViewById<Button>(R.id.btnAddHistoricalSports).setOnClickListener {
            val historyName = dialogView.findViewById<EditText>(R.id.diagHistoryName).text.toString()
            val historyDescription = dialogView.findViewById<EditText>(R.id.diagHistoryDescription).text.toString()
//            val historyDate = dialogView.findViewById<EditText>(R.id.diagHistoryDate).text.toString()
            val historyDate = historyDateEditText.text.toString()
            if(historyName.isNotEmpty() && historyDescription.isNotEmpty() && historyDate.isNotEmpty()){
                val sportHisData = HistoricalSportsData(historyName, historyDate, historyDescription)
                historicalSportsData.add(sportHisData)
                historicalSportsCustomAdapter.notifyItemInserted(historicalSportsData.size - 1)
                dialog.dismiss()
            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}