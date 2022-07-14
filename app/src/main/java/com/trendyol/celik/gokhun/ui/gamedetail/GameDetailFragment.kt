package com.trendyol.celik.gokhun.ui.gamedetail

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.base.view.BaseFragment
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
            swipeRefreshLayout.setOnRefreshListener {
                setUpView()
                setupViewModel()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            var toggle = false
            descriptionCardView.setOnClickListener {
                if (toggle){
                    descriptionTextView.setLines(15)
                    descriptionTextView.movementMethod = ScrollingMovementMethod()
                    toggle = false

                }else {
                    descriptionTextView.setLines(4)
                    toggle = true
                }
            }

            var isFavourite = false
            gameFavouriteImageView.setOnClickListener{
                if (isFavourite){
                    gameFavouriteImageView.setImageResource(R.drawable.favourite)

                    //gameFavourite.copy()
                    //viewModel.insertGame(gameFavourite)

                    isFavourite = false
                }else{
                    gameFavouriteImageView.setImageResource(R.drawable.favourite_not)
                    isFavourite = true
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

            binding.releaseDateTextView.text = it.released?.let { it1 -> formatDate(it1) }


            it.metaCritic.let {

                if(it == null){
                    binding.metaCriticTextView.visibility = View.GONE
                }

                else{
                    binding.metaCriticTextView.visibility = View.VISIBLE
                    binding.metaCriticTextView.text =it.toString()

                    if (it.toString().toInt() in 1..49) {
                        binding.metaCriticTextView.setBackgroundResource(R.drawable.red_mc_bg)
                        binding.metaCriticTextView.setTextColor(ContextCompat.getColor(context!!, R.color.red))
                    }

                    if (it.toString().toInt() in 50..75) {
                        binding.metaCriticTextView.setBackgroundResource(R.drawable.yellow_mc_bg)
                        binding.metaCriticTextView.setTextColor(ContextCompat.getColor(context!!, R.color.yellow))
                    }
                    if (it.toString().toInt() in 75..101) {
                        binding.metaCriticTextView.setBackgroundResource(R.drawable.green_mc_bg)
                        binding.metaCriticTextView.setTextColor(ContextCompat.getColor(context!!, R.color.green))
                    }

                    binding.metaCriticTextView.text =it.toString()
                }
            }

            it.genres.let {
                if (it != null){
                    if (it.isNotEmpty()){
                        binding.genresLayout.visibility = View.VISIBLE
                        var allGenres =""
                        for (item in it!!){
                            allGenres = allGenres + " " + item.name +", "
                        }
                        binding.genresTextView.text = allGenres.dropLast(2)
                    }
                }

            }

            it.playtime.let {
                if(it.toString().toInt() < 1) {
                    binding.playTimeLayout.visibility = View.VISIBLE
                    binding.playTimeTextView.text = it.toString() +" hour"
                }else{
                    binding.playTimeLayout.visibility = View.VISIBLE
                    binding.playTimeTextView.text = it.toString() +" hours"
                }

            }

            it.publishers.let {
                if (it != null){
                    if (it.isNotEmpty()){
                        binding.publishersLayout.visibility = View.VISIBLE
                        var allPublishers =""
                        for (item in it!!){
                            allPublishers = allPublishers + " " + item?.name +", "
                        }
                        binding.publishersTextView.text = allPublishers.dropLast(2)
                    }
                }

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

    private fun formatDate(rawDate: String): String {

        val day = rawDate.split("-")[2]
        var month = rawDate.split("-")[1]
        val year = rawDate.split("-")[0]

        when (month) {
            "01" -> {
                month = "Jan"
            }
            "02" -> {
                month = "Feb"
            }
            "03" -> {
                month = "Mar"
            }
            "04" -> {
                month = "Apr"
            }
            "05" -> {
                month = "May"
            }
            "06" -> {
                month = "Jun"
            }
            "07" -> {
                month = "Jul"
            }
            "08" -> {
                month = "Aug"
            }
            "09" -> {
                month = "Sep"
            }
            "10" -> {
                month = "Okt"
            }
            "11" -> {
                month = "Nov"
            }
            "12" -> {
                month = "Dec"
            }
            else -> {
                month = "Error"
            }
        }

        return "$month $day, $year"
    }


}