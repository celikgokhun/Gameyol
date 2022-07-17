package com.trendyol.celik.gokhun.ui.gamedetail

import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.common.util.*
import com.trendyol.celik.gokhun.common.view.BaseFragment
import com.trendyol.celik.gokhun.databinding.FragmentGameDetailBinding
import com.trendyol.celik.gokhun.domain.model.GameDetail
import com.trendyol.celik.gokhun.ui.gamedetail.viewmodel.GameDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmentGameDetailBinding>() {

    private val viewModel: GameDetailViewModel by viewModels()

    private var gameID = "0"

    override fun init() {
        setUpView()
        setupViewModel()
    }

    private fun setUpView() {
        with(binding) {
            with(swipeRefreshLayout){
                setOnRefreshListener {
                    setUpView()
                    setupViewModel()
                    isRefreshing = false
                }
            }

            var toggle = false
            descriptionCardView.setOnClickListener {
                with(descriptionTextView){
                    if (toggle){
                        setLines(15)
                        movementMethod = ScrollingMovementMethod()
                        toggle = false

                    }else {
                        setLines(4)
                        toggle = true
                    }
                }
            }

            with(gameFavouriteImageView){
                var isFavourite = false
                setOnClickListener{
                    if (isFavourite){
                        setImageResource(R.drawable.favourite)
                        isFavourite = false
                    }else{
                        setImageResource(R.drawable.favourite_not)
                        isFavourite = true
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getStatusViewStateLiveData().observe(viewLifecycleOwner) {
                renderStatusViewState(it)
            }
            getPageViewStateLiveData().observe(viewLifecycleOwner) {
                //renderPageViewState(it)
            }
            arguments?.let {
                gameID = GameDetailFragmentArgs.fromBundle(it).gameID
                initializeViewModel(gameID)
            }
        }
    }

    private fun renderStatusViewState(viewState: GameDetailStatusViewState) = when (viewState) {
        is GameDetailStatusViewState.Loading -> loadingInProgress()
        is GameDetailStatusViewState.Empty -> emptyState()
        is GameDetailStatusViewState.Success -> displayGame(viewState.game)
        is GameDetailStatusViewState .Error -> errorHandle(viewState.throwable)
    }

    private fun loadingInProgress() {
        with(binding){
            gameDetailLayout.visibility = View.GONE
            errorTextView.visibility = View.GONE
            progressBarLoading.visibility = View.VISIBLE
        }
    }

    private fun emptyState() {
        with(binding.errorTextView){
            visibility = View.VISIBLE
            text = "Game Info Empty!" //// ???
        }
    }

    private fun displayGame(game: GameDetail?) {
        game?.let { it->
            with(binding){

                gameDetailLayout.visibility = View.VISIBLE
                errorTextView.visibility = View.GONE
                progressBarLoading.visibility = View.GONE

                gameNameTextView.text = it.nameOriginal
                descriptionTextView.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_LEGACY).toString()
                releaseDateTextView.text = it.released?.let { it1 -> formatDate(it1) }

                it.metaCritic.let {
                    with(metaCriticTextView){
                            if(it == null){
                                visibility = View.GONE
                            } else{
                                visibility = View.VISIBLE
                                text =it.toString()
                                setBackgroundResource(formatMetaCritic(it).first)
                                setTextColor(ContextCompat.getColor(context!!,formatMetaCritic(it).second))
                            }
                        }
                }

                it.genres.let {
                    if (it != null){
                        if (it.isNotEmpty()){
                            genresLayout.visibility = View.VISIBLE
                            genresTextView.text = elementExtractorGenre(it)
                        }
                    }
                }

                it.publishers.let {
                    if (it != null){
                        if (it.isNotEmpty()){
                            publishersLayout.visibility = View.VISIBLE
                            publishersTextView.text = elementExtractorPublisher(it)
                        }
                    }
                }

                it.playtime.let {
                    playTimeLayout.visibility = View.VISIBLE
                    playTimeTextView.text = formatPlayTime(it)
                }

                it.redditUrl.let {
                    with(visitRedditCardView){
                        if (it != null) {
                            if (it.isNotEmpty()){
                                visibility = View.VISIBLE
                            }
                        }
                        setOnClickListener{
                            game.redditUrl?.let { it1 -> startActivity(goToUrl(it1)) }
                        }
                    }
                }

                it.website.let {
                    with(visitWebsiteCardView){
                        if (it != null) {
                            if (it.isNotEmpty()){
                                visibility = View.VISIBLE
                            }
                        }
                        setOnClickListener{
                            game.website?.let { it1 -> startActivity(goToUrl(it1)) }
                        }
                    }
                }

                context?.let {
                    Glide.with(it)
                        .load(game.backGroundImage)
                        .into(gameBGImageView)
                }
            }
        }
    }

    private fun errorHandle(error: Throwable) {
        with(binding.errorTextView){
            visibility = View.VISIBLE
            text = error.localizedMessage
        }
    }
}