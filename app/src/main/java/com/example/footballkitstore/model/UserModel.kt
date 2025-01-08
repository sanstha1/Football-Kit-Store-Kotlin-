package com.example.footballkitstore.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    var userId : String = "",
    var fullname : String = "",
    var gender : String = "",
    var email : String = "",
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?:"",
        parcel.readString() ?:"",
        parcel.readString() ?:"",
        parcel.readString() ?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(fullname)
        parcel.writeString(gender)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

}