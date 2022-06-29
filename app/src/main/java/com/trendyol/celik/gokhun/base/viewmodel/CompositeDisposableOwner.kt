package com.trendyol.celik.gokhun.base.viewmodel

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface CompositeDisposableOwner {

    val disposable: CompositeDisposable

    fun disposeSubscriptions() {
        disposable.dispose()
    }
}