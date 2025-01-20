package com.example.footballkitstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballkitstore.R
import com.example.footballkitstore.model.ProductModel


class ProductAdapter(
    var context: Context,
    var data : ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        val pName : TextView = itemView.findViewById(R.id.productname)
        val pPrice : TextView = itemView.findViewById(R.id.productprice)
        val pDesc : TextView = itemView.findViewById(R.id.productdesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val itemView : View = LayoutInflater.from(context)
            .inflate(R.layout.sample_products,parent,false)

        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.pName.text = data[position].productName
        holder.pPrice.text = data[position].price.toString()
        holder.pDesc.text = data[position].productDesc
    }

    fun updateData(products : List<ProductModel>){
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }
}