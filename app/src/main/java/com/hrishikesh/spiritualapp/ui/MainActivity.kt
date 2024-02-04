package com.hrishikesh.spiritualapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.google.firebase.auth.FirebaseAuth
import com.hrishikesh.spiritualapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed(3000){
            if(mAuth.currentUser != null){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}