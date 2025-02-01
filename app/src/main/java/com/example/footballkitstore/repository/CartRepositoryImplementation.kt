package com.example.footballkitstore.repository

import com.example.footballkitstore.model.CartModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartRepositoryImplementation : CartRepository {

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val ref : DatabaseReference = database.reference.child("cart")

    override fun addToCart(cartModel: CartModel,callback:(Boolean,String)->Unit){
        var  id = ref.push().key.toString()
        cartModel.cartId = id

        ref.child(id).setValue(cartModel).addOnCompleteListener{
            if(it.isSuccessful){
                callback(true,"Item added to cart successfully")
            }else{
                callback(false,"${it.exception?.message}")
            }
        }
    }

    override fun removeAllCart(callback: (Boolean, String) -> Unit) {
        ref.removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "All cart items removed successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun removeCartById(cartId: String, callback: (Boolean, String) -> Unit) {
        ref.child(cartId).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Cart item removed successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun updateCart(cartId: String, data: MutableMap<String, Any>, callback: (Boolean, String) -> Unit) {
        ref.child(cartId).updateChildren(data).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Cart updated successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun getCartById(cartId: String, callback: (CartModel?, Boolean, String) -> Unit) {
        ref.child(cartId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val cartItem = snapshot.getValue(CartModel::class.java)
                    callback(cartItem, true, "Cart item fetched successfully")
                } else {
                    callback(null, false, "No cart item found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null, false, error.message)
            }
        })
    }

    override fun getAllCart(callback: (List<CartModel>?, Boolean, String) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cartItems = mutableListOf<CartModel>()
                if (snapshot.exists()) {
                    for (cartSnapshot in snapshot.children) {
                        val cartItem = cartSnapshot.getValue(CartModel::class.java)
                        if (cartItem != null) {
                            cartItems.add(cartItem)
                        }
                    }
                    callback(cartItems, true, "Cart items fetched successfully")
                } else {
                    callback(null, false, "No cart items found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null, false, error.message)
            }
        })
    }
}