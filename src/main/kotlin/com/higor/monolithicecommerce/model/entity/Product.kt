package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Id

data class Product (
        @Id
        @JsonIgnore
        val id: String? = null,
        val sku: String,
        val name: String,
        var quantity: Long,
        val price: Double
){
    fun getTotalPrice(quantity: Long = this.quantity) : Double = this.price * quantity
    fun quantityIsAvailable(quantity: Long): Boolean = this.quantity >= quantity

}