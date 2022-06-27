package com.trendyol.celik.gokhun.domain.mapper

import javax.inject.Inject
import com.trendyol.celik.gokhun.domain.model.response.list.Result
import com.trendyol.celik.gokhun.domain.model.ui.GameOnList

/*
class GameListMapper @Inject constructor(
    private val priceMapper: ProductPriceMapper,
    private val cartOtherProductsDecider: CartOtherProductsDecider,
) {

    fun mapFromResponse(
        source: Result,
        type: ExpiredBasketResponse
    ): List<GameOnList> {
        return type.products
            ?.filterNotNull()
            ?.map { CartOtherProduct(source = source, basketProduct = createBasketProducts(it)) }
            .orEmpty()
    }
}

 */