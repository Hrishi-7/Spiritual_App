package com.hrishikesh.spiritualapp.ui.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.ui.Fragment.BookFrag.BookDetailsActivity


class BooksFragment : Fragment() {
    private lateinit var gitaCardView : CardView
    private lateinit var ramayanCardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_books, container, false)

        gitaCardView = view.findViewById(R.id.gitaCardView)
        ramayanCardView = view.findViewById(R.id.ramayanCardView)

        gitaCardView.setOnClickListener {
            val intent = Intent(activity, BookDetailsActivity::class.java)
            intent.putExtra("Book", "0")
            startActivity(intent)
        }

        ramayanCardView.setOnClickListener {
            val intent = Intent(activity, BookDetailsActivity::class.java)
            intent.putExtra("Book", "1")
            startActivity(intent)
        }

        return view
    }


}