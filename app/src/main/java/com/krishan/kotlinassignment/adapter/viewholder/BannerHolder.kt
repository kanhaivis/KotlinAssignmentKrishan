package com.krishan.kotlinassignment.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krishan.kotlinassignment.R

class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productName = itemView.findViewById<TextView>(R.id.row_banner_product_name)
    val img = itemView.findViewById<ImageView>(R.id.row_banner_img)
}