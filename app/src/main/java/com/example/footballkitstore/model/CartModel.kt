package com.example.footballkitstore.model

import android.os.Parcel
import android.os.Parcelable

data class CartModel(
    var cartId: String = "",
    var userId: String = "",
    var productName: String = "",
    var productPrice: Int = 0,
    var quantity: Int = 1,
    var productId: String = "",
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",

    ){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cartId)
        parcel.writeString(userId)
        parcel.writeString(productName)
        parcel.writeInt(productPrice)
        parcel.writeInt(quantity)
        parcel.writeString(productId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartModel> {
        override fun createFromParcel(parcel: Parcel): CartModel {
            return CartModel(parcel)
        }

        override fun newArray(size: Int): Array<CartModel?> {
            return arrayOfNulls(size)
        }
    }
}
