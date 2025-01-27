package com.example.footballkitstore.ui.activity

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityForgetPasswordBinding
import com.example.footballkitstore.repository.UserRepositoryImpl
import com.example.footballkitstore.utils.LoadingUtils
import com.example.footballkitstore.viewmodel.UserViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)


        //initializing auth viewmodel
        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)
        loadingUtils = LoadingUtils(this)


        forgetPasswordBinding.resetbutton.setOnClickListener {
            var email: String = forgetPasswordBinding.email.text.toString().trim()

            if (!isValidEmail(email)) {
                Toast.makeText(
                    this@ForgetPasswordActivity,
                    "Please enter a valid email address",
                    Toast.LENGTH_LONG
                ).show()
                forgetPasswordBinding.email.requestFocus()
                return@setOnClickListener
            }

            loadingUtils.show()

            userViewModel.forgetPassword(email) { success, message ->

                loadingUtils.dismiss()

                if (success) {
                    Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_LONG).show()
                }
            }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}