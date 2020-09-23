package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document
data class Cart (
        @Id
        @JsonIgnore
        val id: String? = null,
        val customer: User,
        val products: HashSet<Product> = HashSet<Product>(),
        @JsonProperty("total_price")
        var totalPrice: Double = 0.0
){

    fun calculateTotalPrice() {
        if(this.products.size > 0){
            this.products.forEach { it -> this.totalPrice = this.totalPrice.plus(it.price * it.quantity) }
        }else{
            this.totalPrice = 0.00
        }
    }
}