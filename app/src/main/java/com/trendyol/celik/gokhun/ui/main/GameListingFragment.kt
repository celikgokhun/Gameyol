package com.trendyol.celik.gokhun.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.ui.main.viewmodel.GameListingViewModel
import kotlinx.android.synthetic.main.fragment_game_listing.*
import javax.inject.Inject


class GameListingFragment : Fragment() {

    @Inject
    lateinit var viewModel: GameListingViewModel

    @Inject
    lateinit var gameListingAdapter: GameListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gameListingAdapter = GameListingAdapter()

        viewModel = ViewModelProviders.of(this).get(GameListingViewModel::class.java)
        viewModel.refreshRawgAPIData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewGameList.apply {
            layoutManager = LinearLayoutManager(this@GameListingFragment.context)
            adapter = gameListingAdapter
        }

        recyclerViewGameList.layoutManager = GridLayoutManager(context,2)
        recyclerViewGameList.adapter = gameListingAdapter

        swipeRefreshLayout.setOnRefreshListener {
            progressBarLoading.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            recyclerViewGameList.visibility = View.GONE
            viewModel.refreshRawgAPIData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.rawgData.observe(viewLifecycleOwner) { games ->
            games?.let {
                recyclerViewGameList.visibility = View.VISIBLE
                games.result?.let { it1 -> gameListingAdapter.submitList(it1) }
            }
        }

        viewModel.rawgDataError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    recyclerViewGameList.visibility = View.GONE
                    progressBarLoading.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                } else {
                    errorTextView.visibility = View.GONE
                }
            }
        }

        viewModel.rawgDataLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    recyclerViewGameList.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    progressBarLoading.visibility = View.VISIBLE
                } else {
                    progressBarLoading.visibility = View.GONE
                }
            }
        }
    }

}