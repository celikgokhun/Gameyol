package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.trendyol.celik.gokhun.base.view.BaseFragment
import com.trendyol.celik.gokhun.databinding.FragmentGameListingBinding
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_listing.*
import javax.inject.Inject

@AndroidEntryPoint
class GameListingFragment : BaseFragment<FragmentGameListingBinding>() {

    private val viewModel: GameListingViewModel by viewModels()

    @Inject
    lateinit var gameListingAdapter: GameListingAdapter

    override fun init() {
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

    /*
    private fun renderPageViewState(viewState: GameListingPageViewState) = when (viewState) {
        is GameListingStatusViewState.IsLoading -> loadingInProgress()
        is GameListingStatusViewState.IsDoneLoading -> loadingIsDone()
        is GameListingStatusViewState.ShowData -> displayGames(viewState.games)
        is GameListingStatusViewState.Error -> showError(viewState.error)
    }

     */

    private fun renderPageViewState(viewState: GameListingPageViewState) {
        gameListingAdapter.submitList(viewState.games)
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

}