package com.example.footballkitstore.repository

interface UserRepository {

    //backend vanera samja

    /*{
    "success" : true
    "message" : "Login successfull"

    "statuscode" : 200 then callback int must be added
     */

    fun login(email:String,password:String, callback:(Boolean,String) -> Unit ) //response

    fun signup()

    fun addUserToDatabase()

    fun forgetPassword()

    fun getCurrentUser()
}