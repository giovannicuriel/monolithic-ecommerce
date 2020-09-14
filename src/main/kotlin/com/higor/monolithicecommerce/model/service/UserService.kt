package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.entity.User
import com.higor.monolithicecommerce.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class UserService(@Autowired val repository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = repository.findAll()
    fun create(user: User): User {
        return repository.save(user)
    }
}