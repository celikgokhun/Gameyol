package com.trendyol.celik.gokhun.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.base.recyclerview.BaseListAdapter
import com.trendyol.celik.gokhun.base.recyclerview.DataClassDiffCallback
import com.trendyol.celik.gokhun.domain.model.response.list.Result
import kotlinx.android.synthetic.main.item_game_listing.view.*

import javax.inject.Inject

class GameListingAdapter @Inject constructor() :
    BaseListAdapter<Result,
    GameListingAdapter.GameListingItemViewHolder>(DataClassDiffCallback { it.id }) {

    var gameCardClickListener: ((String, String) -> Unit)? = null

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
                gameCardClickListener?.invoke(getItem(adapterPosition).id.toString(), (adapterPosition + 1).toString())
            }
        }

        fun bind(gameOnList: Result) {
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