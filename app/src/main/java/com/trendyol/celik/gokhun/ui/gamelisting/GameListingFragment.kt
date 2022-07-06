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
        with(binding.recyclerViewGameList) {
            layoutManager =  GridLayoutManager(context,2)
            adapter = gameListingAdapter
        }

        /*
        binding.swipeRefreshLayout.setOnRefreshListener {
            progressBarLoading.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            //recyclerViewGameList.visibility = View.GONE
            setupViewModel()
            swipeRefreshLayout.isRefreshing = false
        }

         */
    }

    private fun setupViewModel() {
        with(viewModel) {
            getStatusViewStateLiveData().observe(viewLifecycleOwner) {
                //renderStatusViewState(it)
            }
            getPageViewStateLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            initializeViewModel()
        }
    }

    private fun renderStatusViewState(viewState: GameListingStatusViewState) = when (viewState) {
        is GameListingStatusViewState.Loading -> loadingInProgress()
        is GameListingStatusViewState.Empty -> emptyState()
        is GameListingStatusViewState.Success -> displayGames(viewState.games)
        is GameListingStatusViewState.Error -> errorHandle(viewState.throwable)
    }

    private fun renderPageViewState(viewState: GameListingPageViewState) {
        gameListingAdapter.submitList(viewState.games)
    }

    private fun errorHandle(error: Throwable) {
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = error.localizedMessage
        }
    }

    private fun loadingInProgress() {
        with(binding){
            progressBarLoading.visibility = View.VISIBLE
        }
    }

    private fun emptyState() {
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = "List Empty Now!" //// ???
        }
    }

    private fun displayGames(games: List<Game>?) {
        games?.let {
            gameListingAdapter.submitList(it)

            println("Malml   "+it)
            binding.recyclerViewGameList.visibility = View.VISIBLE
        }
    }

}