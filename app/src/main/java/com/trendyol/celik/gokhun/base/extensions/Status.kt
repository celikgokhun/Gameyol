package com.trendyol.celik.gokhun.base.extensions

sealed class Status {

    object Content : Status()

    data class Error(val exception: Throwable) : Status()

    object Loading : Status()

    object LoadingWithContent : Status()

    object Empty : Status()

}