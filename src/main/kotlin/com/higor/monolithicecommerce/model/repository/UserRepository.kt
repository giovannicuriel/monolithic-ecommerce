package com.higor.monolithicecommerce.model.repository

import com.higor.monolithicecommerce.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): User?
}