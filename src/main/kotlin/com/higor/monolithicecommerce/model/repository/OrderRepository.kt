package com.higor.monolithicecommerce.model.repository

import com.higor.monolithicecommerce.model.entity.Order
import org.springframework.data.mongodb.repository.MongoRepository

interface OrderRepository : MongoRepository<Order, String>