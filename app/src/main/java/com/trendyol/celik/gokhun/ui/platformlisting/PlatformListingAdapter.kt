package com.trendyol.celik.gokhun.ui.platformlisting

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.WHITE
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.common.adapter.BaseListAdapter
import com.trendyol.celik.gokhun.common.adapter.DataClassDiffCallback
import com.trendyol.celik.gokhun.databinding.ItemPlatformListingBinding
import com.trendyol.celik.gokhun.domain.model.Platform
import javax.inject.Inject

class PlatformListingAdapter @Inject constructor() :
    BaseListAdapter<Platform,
            PlatformListingAdapter.PlatformListingItemViewHolder>(
        DataClassDiffCallback { it.name }
    ) {

    private var isClicked = false

    var onPlatformClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlatformListingAdapter.PlatformListingItemViewHolder {

        return PlatformListingItemViewHolder(
            ItemPlatformListingBinding
                .inflate(LayoutInflater.from(parent.context),
                    parent, false))
    }

    override fun onBindViewHolder(
        holder: PlatformListingItemViewHolder,
        position: Int) {
        holder.bind(getItem(position))
    }



    inner class PlatformListingItemViewHolder(private val binding: ItemPlatformListingBinding):
        RecyclerView.ViewHolder(binding.root){
        init {
            with(binding.root) {
                setTextColor(ContextCompat.getColor(context, R.color.white))

                setOnClickListener {
                    onPlatformClick?.invoke(getItem(bindingAdapterPosition).id)
                    if(isClicked){
                        setTextColor(ContextCompat.getColor(context, R.color.white))
                        isClicked = false
                    }else{
                        setTextColor(ContextCompat.getColor(context, R.color.orange))
                        isClicked = true
                    }
                }
            }
        }
        fun bind(platform: Platform) {
            binding.platformNameTextView.text = platform.name
        }
    }

}