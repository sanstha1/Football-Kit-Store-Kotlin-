package com.example.footballkitstore.repository

import com.example.footballkitstore.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    //MVVM with repository model/pattern

    //backend vanera samja

    /*{
    "success" : true
    "message" : "Login successfull"
    "userId : "1234" "

    "statuscode" : 200 then callback int must be added
     */

    fun login(email:String,password: String,
              callback:(Boolean,String) -> Unit ) //response

    //authentication
    fun signup(email: String,password: String,
               callback: (Boolean, String, String) -> Unit) //   One string for userId

    fun addUserToDatabase(userId:String,userModel: UserModel,
                          callback: (Boolean, String) -> Unit)

    fun forgetPassword(email: String,
                       callback: (Boolean, String) -> Unit)

    fun getCurrentUser() : FirebaseUser?  //? - means that it is nullable
}