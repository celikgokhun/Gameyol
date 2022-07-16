package com.trendyol.celik.gokhun.ui.platformlisting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.celik.gokhun.base.adapter.BaseListAdapter
import com.trendyol.celik.gokhun.base.adapter.DataClassDiffCallback
import com.trendyol.celik.gokhun.databinding.ItemGameListingBinding
import com.trendyol.celik.gokhun.databinding.ItemPlatformListingBinding
import com.trendyol.celik.gokhun.domain.model.Platform
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingAdapter
import javax.inject.Inject

class PlatformListingAdapter @Inject constructor() :
    BaseListAdapter<Platform,
            PlatformListingAdapter.PlatformListingItemViewHolder>(DataClassDiffCallback { it.name })
{

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):
            PlatformListingAdapter.PlatformListingItemViewHolder {
        return PlatformListingItemViewHolder(ItemPlatformListingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlatformListingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlatformListingItemViewHolder(private val binding: ItemPlatformListingBinding):
        RecyclerView.ViewHolder(binding.root){
        init {
            with(binding) {
                root.setOnClickListener {
                    TODO()
                }
            }
        }
        fun bind(platform: Platform) {
            binding.platformNameTextView.text = platform.name
        }
    }

}