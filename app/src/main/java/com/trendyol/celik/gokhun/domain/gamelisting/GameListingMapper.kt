package com.trendyol.celik.gokhun.domain.gamelisting

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