package com.example.footballkitstore.repository

import androidx.recyclerview.widget.ItemTouchHelper.Callback
import com.example.footballkitstore.model.CartModel

interface CartRepository{
    fun addToCart(cartModel: CartModel,
                  callback: (Boolean,String)->Unit)


    fun removeAllCart(callback: (Boolean, String) -> Unit)


    fun removeCartById(cartId: String, callback: (Boolean, String) -> Unit)


    fun updateCart(cartId: String, data: MutableMap<String, Any>, callback: (Boolean, String) -> Unit)


    fun getCartById(cartId: String, callback: (CartModel?, Boolean, String) -> Unit)


    fun getAllCart(callback: (List<CartModel>?, Boolean, String) -> Unit)
}