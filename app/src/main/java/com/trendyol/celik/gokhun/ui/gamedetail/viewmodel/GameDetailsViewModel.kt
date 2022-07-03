package com.trendyol.celik.gokhun.ui.gamedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.ResponseGameDetail
import com.trendyol.celik.gokhun.service.RawgRetrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class GameDetailsViewModel: BaseViewModel(){

    /*
    private val rawgAPIService = RawgRetrofit()

    val gameDetailData = MutableLiveData<ResponseGameDetail>()
    val gameDetailDataError = MutableLiveData<Boolean>()
    val gameDetailDataLoading = MutableLiveData<Boolean>()


    private fun getGameDetailDataAPI(id: String){

        gameDetailDataLoading.value =true

        disposable.add(
            rawgAPIService.fetchDetailsOfGame(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseGameDetail>()
                {
                    override fun onSuccess(t: ResponseGameDetail) {
                        gameDetailData.value = t
                        gameDetailDataError.value = false
                        gameDetailDataLoading.value = false
                        //observeGameDetailData()
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

     */

}