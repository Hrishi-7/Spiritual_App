package com.hrishikesh.spiritualapp.ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.ui.Home.Exercise.ExerciseActivity
import com.hrishikesh.spiritualapp.ui.Home.Quotes.QuotesActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class HomeFragment : Fragment() {
    private lateinit var quotesCard : CardView
    private lateinit var exerciseCard : CardView
    private lateinit var greetingTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        quotesCard = view.findViewById(R.id.quotesCard)
        exerciseCard = view.findViewById(R.id.exerciseCard)
        greetingTv = view.findViewById(R.id.tvGreeting)

        val greeting = getGreeting()
        greetingTv.text = greeting

        quotesCard.setOnClickListener {
            val intent = Intent(activity, QuotesActivity::class.java)
            startActivity(intent)
        }

        exerciseCard.setOnClickListener {
            val intent = Intent(activity, ExerciseActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun getGreeting(): String {
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        return when(hour.toInt()){
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }

}