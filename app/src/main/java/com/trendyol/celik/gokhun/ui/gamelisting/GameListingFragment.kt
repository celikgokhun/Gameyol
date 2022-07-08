package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        with(binding) {
            recyclerViewGameList.layoutManager =  GridLayoutManager(context,2)
            recyclerViewGameList.adapter = gameListingAdapter

            swipeRefreshLayout.setOnRefreshListener {
                setUpView()
                setupViewModel()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            val isLoading = false
            val isLastPage = false
            var isScrolling = false

            recyclerViewGameList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount

                    val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                    val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                    val isNotAtBeginning = firstVisibleItemPosition >= 0
                    val isTotalMoreThanVisible = totalItemCount >= 20
                    val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                            isTotalMoreThanVisible && isScrolling

                    if(shouldPaginate) {
                        //viewModel.get
                        isScrolling = false
                        println("dipledik")
                    } else {
                        viewModel.onNextPage()
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(newState == recyclerViewGameList.scrollState) {
                        isScrolling = true
                    }
                }


            })

        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getStatusViewStateLiveData().observe(viewLifecycleOwner) {
                renderStatusViewState(it)
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
        is GameListingStatusViewState.Success -> displayGames(viewState.games?.games)
        is GameListingStatusViewState.Error -> errorHandle(viewState.throwable)
    }

    private fun renderPageViewState(viewState: GameListingPageViewState) {
        gameListingAdapter.submitList(viewState.games.games)
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

            binding.recyclerViewGameList.visibility = View.VISIBLE
            binding.searchView.clearFocus()
            binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(key: String): Boolean {
                    val filteredList: MutableList<Game> = mutableListOf()
                    for (game in it){
                        if (game.name.lowercase().contains(key.lowercase()) ){
                            filteredList.add(game)
                            gameListingAdapter.submitList(filteredList)
                        }
                    }
                    return true
                }
            })

        }
    }


}