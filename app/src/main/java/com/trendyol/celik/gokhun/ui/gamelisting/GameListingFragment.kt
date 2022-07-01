package com.trendyol.celik.gokhun.ui.gamelisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.common.di.ViewModelInjection
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingGameResponse
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_listing.*
import javax.inject.Inject

@AndroidEntryPoint
class GameListingFragment : Fragment() {

    @Inject
    lateinit var viewModel: GameListingViewModel

    @Inject
    lateinit var gameListingAdapter: GameListingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        setupViewModel()
    }

    private fun setUpView() {
        recyclerViewGameList.apply {
            layoutManager =  GridLayoutManager(context,2)
            adapter = gameListingAdapter
        }

        /*
        swipeRefreshLayout.setOnRefreshListener {
            progressBarLoading.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            recyclerViewGameList.visibility = View.GONE
            //viewModel.refreshRawgAPIData()
            swipeRefreshLayout.isRefreshing = false
        }

         */
    }

    private fun setupViewModel() {
        with(viewModel) {
            getPageViewStateLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            initializeViewModel()
        }
    }

    private fun renderPageViewState(viewState: GameListingPageViewState) = when (viewState) {
        is GameListingPageViewState.IsLoading -> loadingInProgress()
        is GameListingPageViewState.IsDoneLoading -> loadingIsDone()
        is GameListingPageViewState.ShowData -> displayGames(viewState.games)
        is GameListingPageViewState.Error -> showError(viewState.error)
    }

    private fun showError(error: String) {
        context.let {
            Toast.makeText(it, error, Toast.LENGTH_LONG).show()
        }

    }

    private fun loadingInProgress() {
        progressBarLoading.visibility = View.VISIBLE
    }

    private fun loadingIsDone() {
        progressBarLoading.visibility = View.GONE
    }

    private fun displayGames(games: List<Game>?) {
        games?.let {
            gameListingAdapter.submitList(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_listing, container, false)
    }
}