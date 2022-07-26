package com.trendyol.celik.gokhun.ui.gamelisting

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.celik.gokhun.R
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

    private var parentPlatformId : String= ""

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
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false)

                platformListingAdapter.setItemClickListenerByType {
                    filterGamesByPlatform(it.id)
                    queryPlatformsTextViewSetter(it.name)
                }

                adapter = platformListingAdapter
            }

            with(searchView){
                clearFocus()
                setOnQueryTextListener(
                    object :androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(key: String?): Boolean {
                        fullGameList.clear()
                        key?.let {
                            if(parentPlatformId == ""){
                                println("platform yok")
                                fullGameList.clear()
                                queryPlatformsTextView.visibility = View.GONE
                                viewModel.searchGame(it)
                            }else{
                                println("platform VAR")
                                fullGameList.clear()
                                viewModel.searchGameByPlatform(it, parentPlatformId)
                            }
                            isSearchedSomething = true
                            toggleVisibilityBack()
                        }
                        return false
                    }

                    override fun onQueryTextChange(key: String): Boolean {
                        fullGameList.clear()
                        isSearchedSomething = false
                        return true
                    }
                })
            }

            with(swipeRefreshLayout){
                setOnRefreshListener {
                    fullGameList.clear()
                    recyclerViewGameList.adapter?.notifyDataSetChanged()
                    init()
                    isRefreshing = false
                }
            }

            with(buttonBack){
                setOnClickListener {
                    activity?.onBackPressed()
                }
            }
        }
    }

    private var allPlatforms = mutableListOf<String>()
    private fun queryPlatformsTextViewSetter(platform: String){
        with(binding.queryPlatformsTextView){
            visibility = View.VISIBLE
            if (allPlatforms.contains(platform)){
                allPlatforms.remove(platform)
            } else {
                allPlatforms.add(platform)
            }
            text = allPlatforms.toString()
                .replace("[","")
                .replace("]","")
        }
    }

    private var ids = mutableListOf<String>()
    private fun filterGamesByPlatform(id: String) {
        if (ids.contains(id)){
            ids.remove(id)
        } else {
            ids.add(id)
        }
        parentPlatformId = ids.toString()
            .replace("[","")
            .replace(" ","")
            .replace("]","")
    }

    private fun toggleVisibilityBack(){
        with(binding){
            if(isSearchedSomething){
                buttonBack.visibility = View.VISIBLE
                backText.visibility = View.VISIBLE
            }
            else{
                buttonBack.visibility = View.INVISIBLE
                backText.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getStatusViewStateLiveData()
                .observe(viewLifecycleOwner) {
                renderStatusViewState(it)
            }
            getStatusViewPlatformStateLiveData()
                .observe(viewLifecycleOwner) {
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
        binding.progressBarLoading.visibility = View.GONE
        with(binding.errorTextView){
            visibility = View.VISIBLE
            text = error.localizedMessage
        }
    }

    private fun loadingInProgress() {
        with(binding.progressBarLoading){
            visibility = View.VISIBLE
        }
    }

    private fun emptyState() {
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = "List is Empty!"
            progressBarLoading.visibility = View.GONE
            recyclerViewGameList.visibility = View.GONE
        }
    }

    private fun displayGames(games: List<Game>?) {
        games?.let {
            fullGameList = (fullGameList + it).toMutableList()
            with(gameListingAdapter){
                submitList(fullGameList)
                notifyDataSetChanged()
            }

            with(binding){
                recyclerViewGameList.visibility = View.VISIBLE
                errorTextView.visibility = View.GONE
                progressBarLoading.visibility = View.GONE
            }
        }
    }



    private fun displayPlatforms(games: List<Platform>?) {
        games?.let {
            platformListingAdapter.setItems(it)
            binding.recyclerViewPlatformList.visibility = View.VISIBLE
        }
    }

}