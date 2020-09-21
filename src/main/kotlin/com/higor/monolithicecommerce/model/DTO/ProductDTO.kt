package com.higor.monolithicecommerce.model.DTO

import com.higor.monolithicecommerce.model.entity.Product

 data class ProductDTO (
        val sku: String,
        val name: String,
        val quantity: Long,
        val price: Double
){
     fun toEntity(): Product = Product(
             sku = this.sku,
             name = this.name,
             quantity = this.quantity,
             price = this.price
     )
 }