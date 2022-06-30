package com.trendyol.celik.gokhun.ui.gamelisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
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

        viewModel = ViewModelProviders.of(this)[GameListingViewModel::class.java]
        // viewModel.refreshRawgAPIData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewGameList.apply {
            layoutManager =  GridLayoutManager(context,2)
            adapter = gameListingAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            progressBarLoading.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            recyclerViewGameList.visibility = View.GONE
            //viewModel.refreshRawgAPIData()
            swipeRefreshLayout.isRefreshing = false
        }

        //observeLiveData()
    }

    private fun observeLiveData(){

        /*
        viewModel.rawgData.observe(viewLifecycleOwner) { games ->
            games?.let {
                recyclerViewGameList.visibility = View.VISIBLE
                games.results?.let { it1 -> gameListingAdapter.submitList(it1) }
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

         */
    }

}