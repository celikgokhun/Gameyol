package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.base.adapter.BaseListAdapter
import com.trendyol.celik.gokhun.base.adapter.DataClassDiffCallback
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameResultResponse
import kotlinx.android.synthetic.main.item_game_listing.view.*

import javax.inject.Inject

class GameListingAdapter @Inject constructor() :
    BaseListAdapter<GameResultResponse,
    GameListingAdapter.GameListingItemViewHolder>(DataClassDiffCallback { it.id })
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameListingItemViewHolder {
        val rootView  = LayoutInflater.from(parent.context).inflate(R.layout.item_game_listing, parent, false)

        return GameListingItemViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: GameListingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GameListingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {

                val id = getItem(adapterPosition).id.toString()

                val action = GameListingFragmentDirections.actionGameListFragmentToGameDetailFragment(id)
                Navigation.findNavController(itemView).navigate(action)

            }
        }

        fun bind(gameOnList: GameResultResponse) {
            itemView.titleTextView.text = gameOnList.name

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.idle)
                .error(R.drawable.idle)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(gameOnList.backgroundImage)
                .into(itemView.coverImageView)
        }
    }
}