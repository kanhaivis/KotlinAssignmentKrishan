package com.krishan.kotlinassignment.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krishan.kotlinassignment.R

class SplitBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ttitle1 = itemView.findViewById<TextView>(R.id.row_split_banner_1_title)
    val ttitle2 = itemView.findViewById<TextView>(R.id.row_split_banner_2_title)
    val img1 = itemView.findViewById<ImageView>(R.id.row_split_banner_1_img)
    val img2 = itemView.findViewById<ImageView>(R.id.row_split_banner_2_img)
}