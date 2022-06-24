package com.trendyol.celik.gokhun.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trendyol.celik.gokhun.model.RawgData
import com.trendyol.celik.gokhun.service.RawgAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameListViewModel : ViewModel(){

    private val rawgAPIService = RawgAPIService()

    private val disposable = CompositeDisposable()

    val rawgData = MutableLiveData<RawgData>()
    val rawgDataError = MutableLiveData<Boolean>()
    val rawgDataLoading = MutableLiveData<Boolean>()


    private fun getRawgDataAPI(){

        rawgDataLoading.value =true

        disposable.add(
            rawgAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RawgData>()
                {
                    override fun onSuccess(t: RawgData) {
                        rawgData.value = t
                        rawgDataError.value = false
                        rawgDataLoading.value = false
                        observeRawgData()
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



}