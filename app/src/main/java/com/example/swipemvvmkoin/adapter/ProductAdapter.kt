package com.example.swipemvvmkoin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipemvvmkoin.R
import com.example.swipemvvmkoin.model.ProductItem


class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var dataList: List<ProductItem> = emptyList()

    fun updateData(newList: List<ProductItem>) {
        dataList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        val colorArray = arrayOf(R.color.blue,R.color.blue_200,R.color.cyan,R.color.cyan_200,R.color.cyan_400,R.color.magenta,R.color.pink,R.color.yellow)
        val randomColor = colorArray.random()
        holder.productCard.setBackgroundColor(ContextCompat.getColor(holder.productCard.context,randomColor))
        holder.productTitle.text = data.productName
        Glide.with(holder.productImage.context)
            .load(data.image)
            .placeholder(com.example.swipemvvmkoin.R.drawable.ic_launcher_foreground)
            .into(holder.productImage);
        holder.productPrice.text = "Rs "+ data.price.toString()
        holder.productTax.text = data.tax.toString()
        holder.productType.text = data.product_type
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.imageview)
        val productTitle: TextView = itemView.findViewById(R.id.product_name)
        val productType: TextView = itemView.findViewById(R.id.product_type)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productTax: TextView = itemView.findViewById(R.id.product_tax)
        val productCard : ConstraintLayout = itemView.findViewById(R.id.productCard)
    }
}