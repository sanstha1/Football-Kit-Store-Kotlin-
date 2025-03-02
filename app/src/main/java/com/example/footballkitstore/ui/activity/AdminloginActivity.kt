package com.example.footballkitstore.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityAdminloginBinding
import com.example.footballkitstore.databinding.ActivityLoginBinding

class AdminloginActivity : AppCompatActivity() {

    lateinit var binding: ActivityAdminloginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminloginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowback.setOnClickListener{
            val intent = Intent(this@AdminloginActivity, DashboardActivity2::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}