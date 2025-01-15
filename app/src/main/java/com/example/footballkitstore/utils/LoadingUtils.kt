package com.example.footballkitstore.utils

import android.app.Activity
import android.app.AlertDialog
import com.example.footballkitstore.R

class LoadingUtils(val activity : Activity) {
    lateinit var alertDialog: AlertDialog

    fun show(){
        val builder = AlertDialog.Builder(activity)

        val designview = activity.layoutInflater.inflate(
            R.layout.loading,
            null
        )

        builder.setView(designview)
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()



    }
    fun dismiss(){
        alertDialog.dismiss()
    }

}