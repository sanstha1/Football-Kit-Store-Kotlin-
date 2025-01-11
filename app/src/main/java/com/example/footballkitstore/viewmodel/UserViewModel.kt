package com.example.footballkitstore.viewmodel

import com.example.footballkitstore.model.UserModel
import com.example.footballkitstore.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel(val repo :UserRepository) {
    fun login(email:String,password:String,
              callback:(Boolean,String) -> Unit ){
        repo.login(email,password,callback)
    } //response

    //authentication
    fun signup(email: String,password: String,
               callback: (Boolean, String, String) -> Unit) {
        repo.signup(email,password,callback)
    }//   One string for userId

    fun addUserToDatabase(userId:String, userModel: UserModel,
                          callback: (Boolean, String) -> Unit){
        repo.addUserToDatabase(userId,userModel,callback)
    }

    fun forgetPassword(email: String,
                       callback: (Boolean, String) -> Unit){
        repo.forgetPassword(email,callback)
    }

    fun getCurrentUser() : FirebaseUser? {
        return repo.getCurrentUser()
    } //? - means that it is nullable
}