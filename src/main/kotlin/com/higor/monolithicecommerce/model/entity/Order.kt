package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.higor.monolithicecommerce.model.enum.StatusEnum
import javax.persistence.Id

data class Order (
        @Id
        val id: String? = null,
        val customer: User,
        val products: HashSet<Product> = HashSet<Product>(),
        @JsonProperty("total_price")
        val totalPrice: Double,
        var status: StatusEnum = StatusEnum.WAITING_PAYMENT
)