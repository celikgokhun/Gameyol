package com.trendyol.celik.gokhun.ui.platformlisting

import android.view.ViewGroup
import com.trendyol.celik.gokhun.common.adapter.BaseRecyclerViewAdapter
import com.trendyol.celik.gokhun.common.util.inflate
import com.trendyol.celik.gokhun.databinding.ItemPlatformListingBinding
import com.trendyol.celik.gokhun.domain.model.Platform
import javax.inject.Inject

class PlatformListingAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<Platform,
            ItemPlatformListingBinding>() {

    inner class PlatformListingItemViewHolder(private val binding: ItemPlatformListingBinding):
        ViewBindingViewHolder(binding){

        override fun bind(platform: Platform) {
            binding.platformNameTextView.text = platform.name
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return PlatformListingItemViewHolder(parent.inflate(ItemPlatformListingBinding::inflate))
    }

}