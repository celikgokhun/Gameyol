package com.trendyol.celik.gokhun.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.domain.model.ui.GameOnList
import kotlinx.android.synthetic.main.game_row_item.view.*

class GameListAdapter(private val listener: RecycleViewItemClickLister) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var games: List<GameOnList> = emptyList()

    interface RecycleViewItemClickLister {
        fun onRecycleViewItemSelected(item: GameOnList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GamesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GamesViewHolder -> {
                holder.bind(games[position])
                    .setOnClickListener { listener.onRecycleViewItemSelected(games[position]) }

            }
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class GamesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.coverImageView
        private val title = itemView.titleTextView

        fun bind(game: GameOnList): View {
            title.text = game.name

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.idle)
                .error(R.drawable.idle)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(game.backgroundImage)
                .into(image)
            return itemView
        }
    }

    fun updateList(list: List<GameOnList>) {
        games = list
        notifyDataSetChanged()
    }


}