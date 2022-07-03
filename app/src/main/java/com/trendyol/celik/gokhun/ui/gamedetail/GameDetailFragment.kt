package com.trendyol.celik.gokhun.ui.gamedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.ui.gamedetail.viewmodel.GameDetailsViewModel
import kotlinx.android.synthetic.main.fragment_game_detail.*
import kotlinx.android.synthetic.main.fragment_game_detail.errorTextView
import kotlinx.android.synthetic.main.fragment_game_detail.progressBarLoading
import kotlinx.android.synthetic.main.fragment_game_detail.swipeRefreshLayout

import javax.inject.Inject

class GameDetailFragment : Fragment() {

    /*

    @Inject
    lateinit var viewModel: GameDetailsViewModel

    private var gameID = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[GameDetailsViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {
            gameID = GameDetailFragmentArgs.fromBundle(it).gameID
            viewModel.refreshGameDetailAPIData(gameID)
        }

        swipeRefreshLayout.setOnRefreshListener {
            progressBarLoading.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            viewModel.refreshGameDetailAPIData(gameID)
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.gameDetailData.observe(viewLifecycleOwner) { game ->
            game.name.let {
                gameNameTextView.text = it
            }

            game.descriptionRaw.let {
                descriptionTextView.text = it
                println("kamil   " +it)
            }

            game.released.let {
                releaseDateLayout.visibility = View.VISIBLE
                releaseDateTextView.text = it
            }

            game.genres.let {
                genresLayout.visibility = View.VISIBLE
                genresTextView.text = it.toString()
            }

            game.playtime.let {
                playTimeLayout.visibility = View.VISIBLE
                playTimeTextView.text = it.toString()
            }

            game.publishers.let {
                publishersLayout.visibility = View.VISIBLE
                publishersTextView.text = it.toString()
            }

            game.redditUrl.let {
                visitRedditCardView.visibility = View.VISIBLE

                visitRedditCardView.setOnClickListener{
                    game.redditUrl?.let { it1 -> goToUrl(it1) }
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

    fun goToUrl(url:String){
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }catch (e:Exception){

        }
    }

     */


}