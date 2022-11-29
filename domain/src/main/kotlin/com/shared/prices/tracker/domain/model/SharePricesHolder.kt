package com.shared.prices.tracker.domain.model

data class SharePricesHolder(
    val sharePrices: List<SharePrice> = listOf()
) {
    
    fun addSharePrice(sharePrice: SharePrice){
        this.sharePrices.plus(sharePrice)
    }
  
}
