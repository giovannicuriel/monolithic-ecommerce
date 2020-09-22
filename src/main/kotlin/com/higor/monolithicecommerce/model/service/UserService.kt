package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.DTO.UserDTO
import com.higor.monolithicecommerce.model.entity.User
import com.higor.monolithicecommerce.model.repository.UserRepository
import com.higor.monolithicecommerce.model.service.exception.ResourceAlreadyExists
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val repository: UserRepository) {

    fun getAllUsers(): List<User> = repository.findAll()

    fun getUserByEmail(email: String): User? = repository.findByEmail(email)

    private fun userExists(email: String): Boolean = this.getUserByEmail(email) != null

    fun create(userDTO: UserDTO): User {
        if (this.userExists(userDTO.email)){
            throw ResourceAlreadyExists("Usuário já existe com esse email")
        }

        val user = userDTO.toEntity()
        user.passwordEncrypt()
        return repository.save(user)
    }

    fun deleteByEmail(email: String) {
        if(!this.userExists(email)){
            throw ResourceNotFound("Resource Not Found $email")
        }
        val user = this.getUserByEmail(email)
        repository.delete(user!!)
    }
}