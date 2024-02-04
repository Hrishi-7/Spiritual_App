package com.hrishikesh.spiritualapp.ui.Home.Quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.adapter.QuotesFeedAdapter
import com.hrishikesh.spiritualapp.api.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class QuotesActivity : AppCompatActivity() {
    private val quotesFeedAdapter = QuotesFeedAdapter(this, emptyList())
    private val quoteApiClient = Retrofit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val recyclerView : RecyclerView = findViewById(R.id.quotesRV)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = quotesFeedAdapter

        CoroutineScope(Dispatchers.Main).launch {
            try{
                val quotes = withContext(Dispatchers.IO){
                    quoteApiClient.getRandomQuote()
                }
                quotesFeedAdapter.updateQuotes(quotes)
            }catch (e : Exception){
                throw e
            }
        }
    }
}