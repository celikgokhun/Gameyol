package com.trendyol.celik.gokhun.base.viewmodel

import io.reactivex.disposables.CompositeDisposable

interface CompositeDisposableOwner {

    val disposable: CompositeDisposable

    fun disposeSubscriptions() {
        disposable.dispose()
    }
}