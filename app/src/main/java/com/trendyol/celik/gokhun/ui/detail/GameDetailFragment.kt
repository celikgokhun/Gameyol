package com.trendyol.celik.gokhun.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.ui.detail.viewmodel.GameDetailsViewModel
import com.trendyol.celik.gokhun.ui.listing.GameListingAdapter
import com.trendyol.celik.gokhun.ui.listing.viewmodel.GameListingViewModel
import kotlinx.android.synthetic.main.fragment_game_detail.*
import javax.inject.Inject

class GameDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: GameDetailsViewModel

    private var GameID = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[GameDetailsViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            GameID = GameDetailFragmentArgs.fromBundle(it).gameID
            println("incoming   " + GameID)
            viewModel.refreshGameDetailAPIData(GameID)
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.gameDetailData.observe(viewLifecycleOwner) { game ->
            game?.let {
                // recyclerViewGameList.visibility = View.VISIBLE --- adjust visibility

                // need to handle data and adjust view states :d

                game.name?.let {
                    it ->
                    println("name Of :   "+ it)
                }
            }
        }

        viewModel.gameDetailDataError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    // recyclerViewGameList.visibility = View.GONE --- adjust visibility
                    progressBarLoading.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                } else {
                    errorTextView.visibility = View.GONE
                }
            }
        }

        viewModel.gameDetailDataLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    // recyclerViewGameList.visibility = View.GONE --- adjust visibility
                    errorTextView.visibility = View.GONE
                    progressBarLoading.visibility = View.VISIBLE
                } else {
                    progressBarLoading.visibility = View.GONE
                }
            }
        }
    }


}