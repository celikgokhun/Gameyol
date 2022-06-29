package com.trendyol.celik.gokhun.ui.listing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.model.response.list.RawgData
import com.trendyol.celik.gokhun.service.RawgAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class GameListingViewModel : BaseViewModel() {

    private val rawgAPIService = RawgAPIService()


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