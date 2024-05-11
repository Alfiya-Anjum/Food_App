package com.example.food_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // Ensure you have a Toolbar with this ID in your layout
        setSupportActionBar(toolbar)

        // BottomNavigationView setup
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set the default selected item
        bottomNavigationView.selectedItemId = R.id.navigation_home

        // Listener for BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Load HomeFragment
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_notes -> {
                    // Load NotesFragment
                    // Make sure to implement NotesFragment similar to HomeFragment
                    loadFragment(NotesFragment())
                    true
                }
                R.id.navigation_favorites -> {
                    // Load FavoritesFragment
                    // Make sure to implement FavoritesFragment similar to HomeFragment
                    loadFragment(FavoritesFragment())
                    true
                }
                else -> false
            }
        }

        // Ensure the HomeFragment is added only once
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.navigation_home // Set home as default
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Replace the existing fragment with the new fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
