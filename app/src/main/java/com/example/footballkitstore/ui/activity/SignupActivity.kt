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

@Suppress("NAME_SHADOWING")
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

        binding.loginbutton.setOnClickListener{
            var intent = Intent(this@SignupActivity,
                LoginActivity::class.java)
            startActivity(intent)
        }

//        val intent = Intent(this, HomepageActivity::class.java)
//        intent.putExtra("fullName",fullname)


        binding.signupbutton.setOnClickListener{
            loadingUtils.show()
            var email = binding.email.text.toString().trim()
            var password = binding.password.text.toString().trim()
            var fullname = binding.fullname.text.toString().trim()
            var gender = binding.radioGroup.checkedRadioButtonId

            if (email.isEmpty() || password.isEmpty() || fullname.isEmpty() || gender == -1){
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_LONG)
                    .show()
            }

            var gender2 = findViewById<android.widget.RadioButton>(gender).text.toString()

            loadingUtils.show()

            userViewModel.signup(email,password){
                success,message,userId ->
                if(success){
                    var userModel = UserModel(
                            userId.toString(),fullname,
                            gender2,email
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
//                            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
//                            finish()

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