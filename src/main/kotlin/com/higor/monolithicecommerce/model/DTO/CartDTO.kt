package com.higor.monolithicecommerce.model.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import com.higor.monolithicecommerce.model.entity.Cart

data class CartDTO (
        @JsonProperty("user_email")
        val userEmail: String,
        val product: ProductCartDTO
)