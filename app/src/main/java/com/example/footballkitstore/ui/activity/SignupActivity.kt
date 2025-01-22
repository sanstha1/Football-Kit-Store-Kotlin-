package com.example.footballkitstore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.footballkitstore.R
import com.example.footballkitstore.databinding.ActivitySignupBinding
import com.example.footballkitstore.model.UserModel
import com.example.footballkitstore.repository.UserRepositoryImpl
import com.example.footballkitstore.utils.LoadingUtils
import com.example.footballkitstore.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    lateinit var userViewModel: UserViewModel

    lateinit var loadingUtils: LoadingUtils



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils = LoadingUtils(this)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        binding.login.setOnClickListener{
            val intent = Intent(this@SignupActivity,
                LoginActivity::class.java)
            startActivity(intent)
        }

//        val intent = Intent(this, HomepageActivity::class.java)
//        intent.putExtra("fullName",fullname)


        binding.button.setOnClickListener{

            loadingUtils.show()

            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            var fullname = binding.fullname.text.toString()
            var gender = binding.radioGroup.toString()



            userViewModel.signup(email,password){
                success,message,userId ->
                if(success){
                    var userModel = UserModel(
                            userId.toString(),fullname,
                            gender,email
                        )
                    userViewModel.addUserToDatabase(userId,userModel){
                        success,message->
                        if(success){
                            loadingUtils.dismiss()
                            Toast.makeText(
                                this@SignupActivity,
                                message,
                                Toast.LENGTH_LONG
                            ).show()

                            val intent = Intent(this@SignupActivity,
                                LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            loadingUtils.dismiss()
                            Toast.makeText(
                                this@SignupActivity,
                                message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }else{
                    loadingUtils.dismiss()
                    Toast.makeText(
                        this@SignupActivity,
                        message,
                        Toast.LENGTH_LONG
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