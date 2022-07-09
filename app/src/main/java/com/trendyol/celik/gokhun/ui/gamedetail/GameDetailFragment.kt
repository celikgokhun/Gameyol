package com.trendyol.celik.gokhun.ui.gamedetail

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.base.view.BaseFragment
import com.trendyol.celik.gokhun.databinding.FragmentGameDetailBinding
import com.trendyol.celik.gokhun.domain.model.GameDetail
import com.trendyol.celik.gokhun.ui.gamedetail.viewmodel.GameDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jsoup.Jsoup

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
            swipeRefreshLayout.setOnRefreshListener {
                setUpView()
                setupViewModel()
                binding.swipeRefreshLayout.isRefreshing = false
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
        println("boş kanka")
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = "Game Info Empty!" //// ???
        }
    }

    private fun displayGame(game: GameDetail?) {
        binding.gameDetailLayout.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE
        binding.progressBarLoading.visibility = View.GONE
        game?.let {
            binding.gameNameTextView.text = it.nameOriginal
            binding.descriptionTextView.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_LEGACY).toString()
            binding.releaseDateTextView.text = it.released

            it.metaCritic.let {
                binding.metaCriticTextView.visibility = View.VISIBLE
                binding.metaCriticTextView.text =it.toString()
            }

            it.genres.let {
                binding.genresLayout.visibility = View.VISIBLE
                var allGenres =""
                for (item in it!!){
                    allGenres = allGenres + " " + item.name +", "
                }
                binding.genresTextView.text = allGenres.dropLast(2)
            }

            it.playtime.let {
                binding.playTimeLayout.visibility = View.VISIBLE
                binding.playTimeTextView.text = it.toString() +" hours"
            }

            it.publishers.let {
                binding.publishersLayout.visibility = View.VISIBLE
                var allPublishers =""
                for (item in it!!){
                    allPublishers = allPublishers + " " + item?.name +", "
                }
                binding.publishersTextView.text = allPublishers.dropLast(2)
            }

            it.redditUrl.let {
                if (it != null) {
                    if (it.isNotEmpty()){
                        binding.visitRedditCardView.visibility = View.VISIBLE
                    }
                }
                binding.visitRedditCardView.setOnClickListener{
                    game.redditUrl?.let { it1 -> goToUrl(it1) }
                }
            }

            it.website.let {
                if (it != null) {
                    if (it.isNotEmpty()){
                        binding.visitWebsiteCardView.visibility = View.VISIBLE
                    }
                }
                binding.visitWebsiteCardView.setOnClickListener{
                    game.website?.let { it1 -> goToUrl(it1) }
                }
            }

            context?.let {
                Glide.with(it)
                    .load(game.backGroundImage)
                    .into(binding.gameBGImageView)
            }
        }
    }

    private fun errorHandle(error: Throwable) {
        with(binding){
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = error.localizedMessage
        }
    }

    private fun goToUrl(url:String){
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }catch (e:Exception){

        }
    }

}