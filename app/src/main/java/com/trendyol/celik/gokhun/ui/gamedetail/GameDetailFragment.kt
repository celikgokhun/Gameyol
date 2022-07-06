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

@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmentGameDetailBinding>() {

    private val viewModel: GameDetailViewModel by viewModels()

    private var gameID = "0"

    override fun init() {
        setupViewModel()
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
        binding.gameNameTextView.text = viewState.game.nameOriginal
        binding.descriptionTextView.text = viewState.game.description
        binding.releaseDateTextView.text = viewState.game.released

        viewState.game.metaCritic.let {
            binding.metaCriticTextView.visibility = View.VISIBLE
            binding.metaCriticTextView.text =it.toString()
        }


        viewState.game.genres.let {
            binding.genresLayout.visibility = View.VISIBLE
            binding.genresTextView.text = it?.get(0).toString()
        }

        viewState.game.playtime.let {
            binding.playTimeLayout.visibility = View.VISIBLE
            binding.playTimeTextView.text = it.toString()
        }

        viewState.game.publishers.let {
            binding.publishersLayout.visibility = View.VISIBLE
            binding.publishersTextView.text = it.toString()
        }


        viewState.game.redditUrl.let {
            if (it != null) {
                if (it.isNotEmpty()){
                    binding.visitRedditCardView.visibility = View.VISIBLE
                }
            }
            // binding.visitRedditCardView.visibility = View.VISIBLE
            binding.visitRedditCardView.setOnClickListener{
                viewState.game.redditUrl?.let { it1 -> goToUrl(it1) }
            }
        }

        viewState.game.website.let {
            if (it != null) {
                if (it.isNotEmpty()){
                    binding.visitWebsiteCardView.visibility = View.VISIBLE
                }
            }
            binding.visitWebsiteCardView.setOnClickListener{
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
                .into(binding.gameBGImageView)
        }
    }

    private fun goToUrl(url:String){
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }catch (e:Exception){
            // TO-DO
        }
    }

}