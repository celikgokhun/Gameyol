package com.trendyol.celik.gokhun.common

interface Mapper<T,R> {
    fun mapOnResponse(response: T): R
}