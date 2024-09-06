package com.krishan.kotlinassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krishan.kotlinassignment.R
import com.krishan.kotlinassignment.adapter.viewholder.BannerHolder
import com.krishan.kotlinassignment.adapter.viewholder.HorizontalHolder
import com.krishan.kotlinassignment.adapter.viewholder.SplitBannerHolder
import com.krishan.kotlinassignment.modules.ProductModuleItem

class ProductAdapter(private var productItemList: List<ProductModuleItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var BANNER = 1
    private val SPLIT_BANNER = 2
    private val HORIZONTAL_FREE_SCROLL = 3


    fun updateProductList(_productItemList: List<ProductModuleItem>) {
        productItemList = _productItemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BANNER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_single_banner, parent, false)
                return BannerHolder(view)
            }

            SPLIT_BANNER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_slpite_banner, parent, false)
                return SplitBannerHolder(view)
            }

            HORIZONTAL_FREE_SCROLL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_horizontal_image, parent, false)
                return HorizontalHolder(view)
            }

            else -> {
                return null!!
            }
        }
    }

    override fun getItemCount(): Int {
        return productItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = productItemList[position]
        val context = holder.itemView.context

        when (getItemViewType(position)) {
            BANNER -> {
                val bannerHolder = holder as BannerHolder
                val item = productItem.items[0]

                item.title.let {
                    bannerHolder.productName.text = it
                }
                item.image.let {
                    Glide.with(context).load(item.image).into(bannerHolder.img)
                }
            }

            SPLIT_BANNER -> {
                val splitHolder = holder as SplitBannerHolder
                for ((pos, items) in productItem.items.withIndex()) {
                    if (pos ==0) {
                        items.title.let {
                            splitHolder.ttitle1.text = it
                        }
                        items.image.let {
                            Glide.with(context).load(items.image).into(splitHolder.img1)
                        }
                    } else if (pos ==1) {
                        items.title.let {
                            splitHolder.ttitle2.text = it
                        }
                        items.image.let {
                            Glide.with(context).load(items.image).into(splitHolder.img2)
                        }
                    }
                }
            }

            else -> {
                val horizontalHolder = holder as HorizontalHolder
                val item = productItem.items
                val adapter = BannerAdapter(item)
                horizontalHolder.horizontalRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
                horizontalHolder.horizontalRecyclerView.adapter = adapter
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (productItemList[position].sectionType.equals("banner")) {
            return BANNER
        } else if (productItemList[position].sectionType.equals("splitBanner")) {
            return SPLIT_BANNER
        } else if (productItemList[position].sectionType.equals("horizontalFreeScroll")) {
            return HORIZONTAL_FREE_SCROLL
        }
        return null!!
    }
}






