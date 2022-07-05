package com.trendyol.celik.gokhun.ui.gamedetail

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.base.view.BaseFragment
import com.trendyol.celik.gokhun.databinding.FragmentGameDetailBinding
import com.trendyol.celik.gokhun.ui.gamedetail.viewmodel.GameDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_detail.*

@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmentGameDetailBinding>() {

    private val viewModel: GameDetailViewModel by viewModels()

    private var gameID = "0"

    override fun init() {
        setUpView()
        setupViewModel()
    }

    private fun setUpView() {

    }

    private fun setupViewModel() {
        with(viewModel) {
            getPageViewStateLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            arguments?.let {
                gameID = GameDetailFragmentArgs.fromBundle(it).gameID
                initializeViewModel(gameID)
            }
        }
    }

    private fun renderPageViewState(viewState: GameDetailPageViewState) {
        gameNameTextView.text = viewState.game.nameOriginal
        descriptionTextView.text = viewState.game.description
        releaseDateTextView.text = viewState.game.released

        viewState.game.metaCritic.let {
            metaCriticTextView.visibility = View.VISIBLE
            metaCriticTextView.text =it.toString()
        }


        viewState.game.genres.let {
            genresTextView.visibility = View.VISIBLE
            genresTextView.text = it?.get(0).toString()
        }

        viewState.game.playtime.let {
            playTimeTextView.visibility = View.VISIBLE
            playTimeTextView.text = it.toString()
        }

        viewState.game.publishers.let {
            publishersTextView.visibility = View.VISIBLE
            publishersTextView.text = it.toString()
        }


        viewState.game.redditUrl.let {
            visitRedditCardView.visibility = View.VISIBLE

            visitRedditCardView.setOnClickListener{
                viewState.game.redditUrl?.let { it1 -> goToUrl(it1) }
            }

        }

        viewState.game.website.let {
            visitWebsiteCardView.visibility = View.VISIBLE

            visitWebsiteCardView.setOnClickListener{
                viewState.game.website?.let { it1 -> goToUrl(it1) }
            }
        }



        val requestOptions = RequestOptions()
            .placeholder(R.drawable.idle)
            .error(R.drawable.idle)

        context?.let {
            Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
                .load(viewState.game.backGroundImage)
                .into(gameBGImageView)
        }




    }


    /*


        arguments?.let {
            gameID = GameDetailFragmentArgs.fromBundle(it).gameID
            viewModel.refreshGameDetailAPIData(gameID)
        }
    }





            game.website.let {
                visitWebsiteCardView.visibility = View.VISIBLE

                visitWebsiteCardView.setOnClickListener{
                    game.website?.let { it1 -> goToUrl(it1) }
                }
            }



            val requestOptions = RequestOptions()
                .placeholder(R.drawable.idle)
                .error(R.drawable.idle)

            context?.let {
                Glide.with(it)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(game.backgroundImage)
                    .into(gameBGImageView)
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



     */

    fun goToUrl(url:String){
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }catch (e:Exception){

        }
    }


}