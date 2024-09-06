package com.krishan.kotlinassignment.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.krishan.kotlinassignment.R

class HorizontalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val horizontalRecyclerView = itemView.findViewById<RecyclerView>(R.id.row_horizontal_recyclerview)
}