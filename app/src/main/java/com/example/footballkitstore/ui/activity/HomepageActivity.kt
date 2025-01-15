package com.example.footballkitstore.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityHomepageBinding
import com.example.footballkitstore.ui.fragment.HomeFragment
import com.example.footballkitstore.ui.fragment.MatchedFragment
import com.example.footballkitstore.ui.fragment.ProfileFragment

class HomepageActivity : AppCompatActivity() {

        lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomepageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setContentView(R.layout.activity_homepage)
        val fullName = intent.getStringExtra("fullName")

        findViewById<TextView>(R.id.welcome).text = "Welcome, $fullName"






        replaceFragment(HomeFragment())

        binding.buttonNavigationBar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home ->replaceFragment(HomeFragment())
                R.id.matched -> replaceFragment(MatchedFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}