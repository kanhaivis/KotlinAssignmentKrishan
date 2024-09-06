package com.krishan.kotlinassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krishan.kotlinassignment.R
import com.krishan.kotlinassignment.modules.Item

class BannerAdapter(private val bannerItems: List<Item>) :
    RecyclerView.Adapter<BannerAdapter.HorizontalBannerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalBannerHolder {
        return HorizontalBannerHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.horizontal_banner_layout, null, false)
        )
    }

    override fun getItemCount(): Int {
        return bannerItems.size
    }

    override fun onBindViewHolder(holder: HorizontalBannerHolder, position: Int) {
        val item = bannerItems[position]
        holder.title.text = item.title
        Glide.with(holder.itemView.context).load(item.image).into(holder.img)
    }


    class HorizontalBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.row_horizontal_product_txt)
        val img = itemView.findViewById<ImageView>(R.id.row_horizontal_free_img)
    }
}