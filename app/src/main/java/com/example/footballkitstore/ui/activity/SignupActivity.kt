package com.example.footballkitstore.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener{
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(){
                    if(it.isSuccessful){
                        Toast.makeText(
                            this@SignupActivity,"Registration Success",Toast.LENGTH_LONG
                        ).show()
                    }else{
                        Toast.makeText(
                            this@SignupActivity,
                            it.exception?.message,Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}