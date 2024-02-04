package com.hrishikesh.spiritualapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hrishikesh.spiritualapp.R
import com.hrishikesh.spiritualapp.api.QuoteResponse

class QuotesFeedAdapter(var context: Context, var quotesList: List<QuoteResponse>) :
RecyclerView.Adapter<QuotesFeedAdapter.MyViewModel>(){

    class MyViewModel(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.titleTv)
        val author : TextView = itemView.findViewById(R.id.authorTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(LayoutInflater.from(context).inflate(R.layout.card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val currentQuote = quotesList[position]
        holder.title.text = currentQuote.content
        holder.author.text = currentQuote.author
    }

    fun updateQuotes(newQuotes: List<QuoteResponse>){
        quotesList = newQuotes
        notifyDataSetChanged()
    }
}