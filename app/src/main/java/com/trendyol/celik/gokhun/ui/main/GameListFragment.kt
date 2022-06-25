package com.trendyol.celik.gokhun.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.domain.model.ui.GameOnList
import com.trendyol.celik.gokhun.ui.main.viewmodel.GameListViewModel
import javax.inject.Inject


class GameListFragment : Fragment(), GameListAdapter.RecycleViewItemClickLister {

    @Inject
    lateinit var viewModel: GameListViewModel

    private val gamesAdapter: GameListAdapter by lazy {
        GameListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game_list, container, false)
    }

    override fun onRecycleViewItemSelected(item: GameOnList) {

    }


}