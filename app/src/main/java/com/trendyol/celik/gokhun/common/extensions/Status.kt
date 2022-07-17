package com.trendyol.celik.gokhun.common.extensions

sealed class Status {

    object Content : Status()

    data class Error(val exception: Throwable) : Status()

    object Loading : Status()

}