package com.higor.monolithicecommerce.model.repository

import com.higor.monolithicecommerce.model.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String>{
    fun findBySku(sku: String): Product?
}