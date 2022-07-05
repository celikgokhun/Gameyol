package com.trendyol.celik.gokhun.ui.gamelisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingPageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class GameListingViewModel @Inject constructor(
    private val gameListingUseCase: GameListingUseCase
) : BaseViewModel() {

    private val pageViewStateLiveData: MutableLiveData<GameListingPageViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<GameListingPageViewState> = pageViewStateLiveData

    fun initializeViewModel() {
        if (pageViewStateLiveData.value == null) {
            fetchGameList()
        }
    }

    private fun fetchGameList() {
        onVideoListingLoading()
        gameListingUseCase.fetchGames()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onGameListingResponseReady(it)
                },
                {
                    onGameListingResponseFail(it)
                }
            )
    }

    private fun onGameListingResponseReady(gameListing: List<Game>) {
        if (gameListing.isEmpty()) {
            // to-do
        } else {
            pageViewStateLiveData.value = GameListingPageViewState(gameListing)
        }

        /*
        pageViewStateLiveData.value = pageViewStateLiveData.value?.addNewPage(gameListing)
            ?: GameListingPageViewState(gameListing)

         */
    }

    private fun onGameListingResponseFail(throwable: Throwable) {
        /*
        pageViewStateLiveData.value = throwable.localizedMessage?.let {
            GameListingStatusViewState.Error(
                it
            )
        }

         */
    }

    private fun onVideoListingLoading() {
        //pageViewStateLiveData.value = GameListingStatusViewState.IsLoading
    }

    /*

    private val rawgAPIService = RawgAPIService()


    val rawgData = MutableLiveData<GameListingResponse>()
    val rawgDataError = MutableLiveData<Boolean>()
    val rawgDataLoading = MutableLiveData<Boolean>()


    private fun getRawgDataAPI(){

        rawgDataLoading.value =true

        disposable.add(
            rawgAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GameListingResponse>()
                {
                    override fun onSuccess(t: GameListingResponse) {
                        rawgData.value = t
                        rawgDataError.value = false
                        rawgDataLoading.value = false
                        //observeRawgData()
                    }
                    override fun onError(e: Throwable) {
                        println("Error   :  "+ e.localizedMessage )
                        rawgDataLoading.value = false
                        rawgDataError.value = true
                    }
                })
        )

    }

    private fun observeRawgData(){
        println("  :   "+rawgData.value)
    }

    fun refreshRawgAPIData(){
        getRawgDataAPI()
    }

     */
}