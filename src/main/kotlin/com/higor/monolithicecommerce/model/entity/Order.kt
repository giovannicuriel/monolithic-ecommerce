package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.higor.monolithicecommerce.model.enum.StatusEnum

data class Order (
        val customer: User,
        val products: HashSet<Product> = HashSet<Product>(),
        val totalPrice: Double,
        var status: StatusEnum = StatusEnum.WAITING_PAYMENT


){
        fun toJson(): String? {
                val mapper = jacksonObjectMapper()
                return mapper.writeValueAsString(this)
        }
}