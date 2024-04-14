package com.kevinmuchene.sportsnewsandinformationapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.sportsnewsandinformationapp.data.NewsData
import com.kevinmuchene.sportsnewsandinformationapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {


    private lateinit var binding: FragmentNewsBinding

    private lateinit var newsData: ArrayList<NewsData>

    private lateinit var newsAdapter: NewsCustomAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsData = ArrayList<NewsData>()

        newsData.add(
            NewsData("https://img.bleacherreport.net/img/slides/photos/004/479/465/0f84e3e3e1cfd8016eee11a480d5228d_crop_exact.jpg?w=2975&h=2048&q=85", "Match Of The Year (EPL)", """The England Premier League's match of the year featuring Liverpool was a thrilling encounter. Liverpool displayed spectacular teamwork and resilience, captivating fans with their strategic play and exceptional skill. The match underscored their prowess and left an indelible mark on the season, showcasing top-tier football at its best.""".trimIndent())
        )

        newsData.add(
            NewsData("https://assets.apnews.com/73/4a/76ec62203bd068373444160bbea2/17fe2623ddc548658d9fa81f5e838029", "Iowa Are On The Rise", """The Iowa basketball team experienced a notable rise, showcasing significant improvement and competitiveness in their recent season. Driven by a blend of skilled recruits and seasoned players, they demonstrated a robust offense and tightened defense. Their games attracted national attention as they climbed the rankings, signaling a promising future for the program.""".trimIndent())
        )

        newsData.add(
            NewsData("https://img.covers.com/cms/covers/7b6c9f61-14a2-480c-a552-3bbb62f485b6..jpg?w=1421&auto=compress&auto=format", "Clash Of The Titan (Summer)", """The Iowa basketball team experienced a notable rise, showcasing significant improvement and competitiveness in their recent season. Driven by a blend of skilled recruits and seasoned players, they demonstrated a robust offense and tightened defense. Their games attracted national attention as they climbed the rankings, signaling a promising future for the program""".trimIndent())
        )

        newsAdapter = NewsCustomAdapter(newsData)

        binding.newsRecyclerViewId.layoutManager = LinearLayoutManager(requireContext());

        binding.newsRecyclerViewId.adapter = newsAdapter

        binding.newsFabId.setOnClickListener {
            displayAddNewsDialog()
        }


    }

    private fun displayAddNewsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_news, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        dialogView.findViewById<Button>(R.id.btnAddNews).setOnClickListener {
            val imageUrl = dialogView.findViewById<EditText>(R.id.diagNewsImage).text.toString()
            val title = dialogView.findViewById<EditText>(R.id.diagNewsTitle).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.diagNewsDescription).text.toString()

            if(imageUrl.isNotEmpty() && title.isNotEmpty() && description.isNotEmpty()) {

                    val newData = NewsData(imageUrl, title, description)
                    newsData.add(newData)
                    newsAdapter.notifyItemInserted(newsData.size - 1)
                    dialog.dismiss()

            } else {
                Snackbar.make(binding.root, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }


}