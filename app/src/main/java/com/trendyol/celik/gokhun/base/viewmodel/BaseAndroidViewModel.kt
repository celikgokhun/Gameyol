package com.trendyol.celik.gokhun.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application), CompositeDisposableOwner {

    override val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeSubscriptions()
    }
}