package com.higor.monolithicecommerce.model.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class CartDTO (
        @JsonProperty("user_email")
        val userEmail: String,
        val product: ProductCartDTO,
        val voucher: String? = null
)