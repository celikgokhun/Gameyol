package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.View
import androidx.core.view.isEmpty
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

    private var fullGameList: MutableList<Game> = mutableListOf()

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

            recyclerViewGameList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val layoutManager = recyclerViewGameList.layoutManager as GridLayoutManager
                        val visibleItemCount = layoutManager.findLastCompletelyVisibleItemPosition()+1
                        if (visibleItemCount == layoutManager.itemCount){
                            viewModel.onNextPage()
                        }
                    }
                }
            })

            searchView.clearFocus()
            binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(key: String?): Boolean {

                    fullGameList.clear()
                    if (key?.isNotEmpty() == true){

                        viewModel.searchGame(key)
                        binding.recyclerViewGameList.visibility = View.VISIBLE
                        binding.errorTextView.visibility = View.GONE
                        gameListingAdapter.submitList(fullGameList)

                        if (fullGameList.isEmpty()){
                            binding.recyclerViewGameList.visibility = View.GONE
                            binding.errorTextView.visibility = View.VISIBLE
                            binding.errorTextView.text = "No game has been found !"
                        }

                    }
                    else{
                        binding.recyclerViewGameList.visibility = View.VISIBLE
                        binding.errorTextView.visibility = View.GONE
                        gameListingAdapter.submitList(fullGameList)
                    }

                    return false
                }

                override fun onQueryTextChange(key: String): Boolean {
                    if(key.isEmpty()){
                        gameListingAdapter.submitList(fullGameList)
                    }
                    return true
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
        if (viewState.gameListing.games.isNotEmpty()){
            with(binding) {
                progressBarLoading.visibility = View.GONE
            }
        }
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
            errorTextView.text = "List Empty Now!" //// ???
        }
    }

    private fun displayGames(games: List<Game>?) {
        games?.let {
            fullGameList = (fullGameList + it).toMutableList()
            gameListingAdapter.submitList(fullGameList)
            binding.recyclerViewGameList.visibility = View.VISIBLE
        }
    }


}