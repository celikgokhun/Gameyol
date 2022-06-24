package com.trendyol.celik.gokhun.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trendyol.celik.gokhun.model.response.detail.Game
import com.trendyol.celik.gokhun.service.RawgAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameDetailsViewModel : ViewModel(){

    private val rawgAPIService = RawgAPIService()

    private val disposable = CompositeDisposable()

    val gameDetailData = MutableLiveData<Game>()
    val gameDetailDataError = MutableLiveData<Boolean>()
    val gameDetailDataLoading = MutableLiveData<Boolean>()


    private fun getGameDetailDataAPI(id: String){

        gameDetailDataLoading.value =true

        disposable.add(
            rawgAPIService.getDetailsOfGame(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Game>()
                {
                    override fun onSuccess(t: Game) {
                        gameDetailData.value = t
                        gameDetailDataError.value = false
                        gameDetailDataLoading.value = false
                        observeGameDetailData()
                    }
                    override fun onError(e: Throwable) {
                        println("Error   :  "+ e.localizedMessage )
                        gameDetailDataLoading.value = false
                        gameDetailDataError.value = true
                    }
                })
        )

    }

    private fun observeGameDetailData(){
        println("  :   "+gameDetailData.value)
    }

    fun refreshGameDetailAPIData(id :String){
        getGameDetailDataAPI(id)
    }

}