package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.View
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.celik.gokhun.common.view.BaseFragment
import com.trendyol.celik.gokhun.databinding.FragmentGameListingBinding
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.domain.model.Platform
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
import com.trendyol.celik.gokhun.ui.platformlisting.PlatformListingAdapter
import com.trendyol.celik.gokhun.ui.platformlisting.PlatformListingStatusViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameListingFragment : BaseFragment<FragmentGameListingBinding>() {

    private val viewModel: GameListingViewModel by viewModels()

    private var fullGameList: MutableList<Game> = mutableListOf()

    private var isSearchedSomething : Boolean = false

    @Inject
    lateinit var gameListingAdapter: GameListingAdapter

    @Inject
    lateinit var platformListingAdapter: PlatformListingAdapter

    override fun init() {
        setUpView()
        setupViewModel()
    }


    private fun setUpView() {
        with(binding) {

            with(recyclerViewGameList){
                layoutManager =  GridLayoutManager(context,2)
                adapter = gameListingAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            val layoutManager = layoutManager as GridLayoutManager
                            val visibleItemCount = layoutManager.findLastCompletelyVisibleItemPosition()+1
                            if (visibleItemCount == layoutManager.itemCount){

                                if(searchView.query.isEmpty()){
                                    viewModel.onNextGamePage()
                                }
                                if (isSearchedSomething){
                                    viewModel.onNextSearchGame()
                                }

                            }
                        }
                    }
                })
            }

            with(recyclerViewPlatformList){
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = platformListingAdapter
            }

            with(searchView){
                clearFocus()
                setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(key: String?): Boolean {
                        fullGameList.clear()
                        key?.let {
                            viewModel.searchGame(it)
                            isSearchedSomething = true
                        }

                        recyclerViewGameList.visibility = View.GONE
                        errorTextView.visibility = View.GONE
                        progressBarLoading.visibility = View.GONE
                        gameListingAdapter.submitList(fullGameList)

                        return false
                    }

                    override fun onQueryTextChange(key: String): Boolean {
                        if(key.isEmpty()){
                            isSearchedSomething = false
                        }
                        return true
                    }

                })
            }

            with(swipeRefreshLayout){
                setOnRefreshListener {
                    setUpView()
                    setupViewModel()
                    gameListingAdapter.submitList(fullGameList)
                    isRefreshing = false
                }
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getStatusViewStateLiveData().observe(viewLifecycleOwner) {
                renderStatusViewState(it)
            }
            getStatusViewPlatformStateLiveData().observe(viewLifecycleOwner) {
                renderStatusViewPlatformState(it)
            }
            initializeViewModel()
        }
    }

    private fun renderStatusViewPlatformState(viewState: PlatformListingStatusViewState) = when (viewState) {
        is PlatformListingStatusViewState.Loading -> loadingInProgress()
        is PlatformListingStatusViewState.Empty -> emptyState()
        is PlatformListingStatusViewState.Success -> displayPlatforms(viewState.platforms?.platforms)
        is PlatformListingStatusViewState.Error -> errorHandle(viewState.throwable)
    }


    private fun renderStatusViewState(viewState: GameListingStatusViewState) = when (viewState) {
        is GameListingStatusViewState.Loading -> loadingInProgress()
        is GameListingStatusViewState.Empty -> emptyState()
        is GameListingStatusViewState.Success -> displayGames(viewState.games?.games)
        is GameListingStatusViewState.Error -> errorHandle(viewState.throwable)
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
            if (recyclerViewGameList.isEmpty()){
                recyclerViewGameList.visibility = View.GONE
            }
        }
    }

    private fun emptyState() {
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = "List Empty Now!"
            progressBarLoading.visibility = View.GONE
            recyclerViewGameList.visibility = View.GONE
            recyclerViewPlatformList.visibility = View.GONE
        }
    }

    private fun displayGames(games: List<Game>?) {
        games?.let {
            fullGameList = (fullGameList + it).toMutableList()
            gameListingAdapter.submitList(fullGameList)

            with(binding){
                recyclerViewGameList.visibility = View.VISIBLE
                errorTextView.visibility = View.GONE
                progressBarLoading.visibility = View.GONE
            }
        }
    }

    private fun displayPlatforms(games: List<Platform>?) {
        games?.let {
            platformListingAdapter.submitList(it)
            binding.recyclerViewPlatformList.visibility = View.VISIBLE
        }
    }


}