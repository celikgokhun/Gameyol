package com.trendyol.celik.gokhun.ui.platformlisting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.celik.gokhun.common.adapter.BaseListAdapter
import com.trendyol.celik.gokhun.common.adapter.DataClassDiffCallback
import com.trendyol.celik.gokhun.databinding.ItemPlatformListingBinding
import com.trendyol.celik.gokhun.domain.model.Platform
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingFragmentDirections
import javax.inject.Inject

class PlatformListingAdapter @Inject constructor() :
    BaseListAdapter<Platform,
            PlatformListingAdapter.PlatformListingItemViewHolder>(
    DataClassDiffCallback { it.name }
) {

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
            with(binding) {
                root.setOnClickListener {
                    onPlatformClick?.invoke(getItem(bindingAdapterPosition)
                        .name.replace(" ","").lowercase())
                }
            }
        }
        fun bind(platform: Platform) {
            binding.platformNameTextView.text = platform.name
        }
    }

}