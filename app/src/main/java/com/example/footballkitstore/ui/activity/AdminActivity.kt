package com.example.footballkitstore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityAdminBinding
import com.example.footballkitstore.repository.UserRepositoryImpl
import com.example.footballkitstore.utils.LoadingUtils
import com.example.footballkitstore.viewmodel.UserViewModel

class AdminActivity : AppCompatActivity() {

    lateinit var binding: ActivityAdminBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)
        loadingUtils = LoadingUtils(this)

        binding.loginadmin.setOnClickListener {
            var email : String = binding.adminemail.text.toString()
            var password : String = binding.adminpassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_LONG)
                    .show()
            }

            loadingUtils.show()

            userViewModel.login(email, password) { success, message ->
                loadingUtils.dismiss()
                if (success) {
                    //dismiss
                    Toast.makeText(
                        this@AdminActivity, message,
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@AdminActivity, ProductDashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    //dismiss
                    Toast.makeText(this@AdminActivity, message, Toast.LENGTH_LONG).show()
                }
            }
        }



        binding.arrowbackadmin.setOnClickListener{
            val intent = Intent(this@AdminActivity,DashboardActivity2::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}