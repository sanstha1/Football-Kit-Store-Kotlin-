package com.example.footballkitstore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivityLoginBinding
import com.example.footballkitstore.repository.UserRepositoryImpl
import com.example.footballkitstore.viewmodel.UserViewModel
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        loginBinding.login.setOnClickListener{
            //show
            var email : String = loginBinding.email.text.toString()
            var password : String = loginBinding.password.text.toString()

            userViewModel.login(email,password){
                success,message->
                if(success){
                    //dismiss
                    Toast.makeText(this@LoginActivity,message,
                        Toast.LENGTH_LONG).show()

                    var intent = Intent(this@LoginActivity,HomepageActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    //dismiss
                    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                }
            }
        }

        loginBinding.forgotpassword.setOnClickListener{
            val intent = Intent(this@LoginActivity,
                ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        loginBinding.register.setOnClickListener{
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}