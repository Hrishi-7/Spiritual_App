package com.hrishikesh.spiritualapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hrishikesh.spiritualapp.R

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNav = findViewById(R.id.bottomNavBar)
        val navController : NavController = findNavController(R.id.fragmentContainerView)
        bottomNav.setupWithNavController(navController)
    }
}