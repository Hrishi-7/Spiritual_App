package com.hrishikesh.spiritualapp.ui.Fragment.BookFrag

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.storage.FirebaseStorage
import com.hrishikesh.spiritualapp.R

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var bookImage : ImageView
    private lateinit var bookActTypeTv : TextView
    private lateinit var bookActBookTitleTv : TextView
    private lateinit var bookActBookDescTv : TextView
    private lateinit var bookActDownloadBtn : Button
    private lateinit var descArrayList: ArrayList<Int>
    private lateinit var storage : FirebaseStorage
    private var idx : Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val s = intent.extras?.getString("Book")
        idx = s!!.toInt()

        bookImage = findViewById(R.id.bookImage)
        bookActTypeTv = findViewById(R.id.bookActTypeTv)
        bookActBookTitleTv = findViewById(R.id.bookActBookTitleTv)
        bookActBookDescTv = findViewById(R.id.bookActBookDescTv)
        bookActDownloadBtn = findViewById(R.id.bookActDownloadBtn)

        storage = FirebaseStorage.getInstance()

        val imgArr = arrayOf(
            R.drawable.gita_desc,
            R.drawable.hanumadramayan
        )

        val titleArr = arrayOf(
            "Bhagwat Gita",
            "Ramayana"
        )

        val httpRefArr = arrayOf("https://firebasestorage.googleapis.com/v0/b/spiritual-app-56c31.appspot.com/o/Bhagavad-gita-hindi.pdf?alt=media&token=277c4796-b8b1-49d4-ba41-c3c0d6e7dfa3")

        val stringArr = resources.getStringArray(R.array.string_arr)
        val stringVal = stringArr[idx]
        bookActBookDescTv.text = stringVal

        bookActBookTitleTv.text = titleArr[idx]

        bookImage.setImageResource(imgArr[idx])
        if(idx in 0..2){
            bookActTypeTv.text = "Religious"
        }else{
            bookActTypeTv.text = "Self Help"
        }

        bookActDownloadBtn.setOnClickListener {
            val httpReference = storage.getReferenceFromUrl(httpRefArr[idx])

        }
    }
}