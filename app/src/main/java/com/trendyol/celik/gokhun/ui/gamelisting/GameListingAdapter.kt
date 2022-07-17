package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.common.adapter.BaseListAdapter
import com.trendyol.celik.gokhun.common.adapter.DataClassDiffCallback
import com.trendyol.celik.gokhun.databinding.ItemGameListingBinding
import com.trendyol.celik.gokhun.domain.model.Game

import javax.inject.Inject

class GameListingAdapter @Inject constructor() :
    BaseListAdapter<Game,
            GameListingAdapter.GameListingItemViewHolder>(DataClassDiffCallback { it.id })
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):
            GameListingItemViewHolder {
        return GameListingItemViewHolder(ItemGameListingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameListingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GameListingItemViewHolder(private val binding: ItemGameListingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.setOnClickListener {
                    val id = getItem(bindingAdapterPosition).id.toString()
                    val action = GameListingFragmentDirections.actionGameListFragmentToGameDetailFragment(id)
                    Navigation.findNavController(itemView).navigate(action)
                }
            }
        }

        fun bind(game: Game) {
            binding.titleTextView.text = game.name

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.idle)
                .error(R.drawable.idle)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(game.backgroundImage)
                .into(binding.coverImageView)
        }
    }
}