package com.higor.monolithicecommerce.model.repository

import com.higor.monolithicecommerce.model.entity.Cart
import com.higor.monolithicecommerce.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface CartRepository : MongoRepository<Cart, String>{
    fun getByCustomer(user: User): Cart?
}