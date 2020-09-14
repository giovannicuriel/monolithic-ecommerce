package com.higor.monolithicecommerce.resource

import com.higor.monolithicecommerce.model.DTO.UserDTO
import com.higor.monolithicecommerce.model.entity.User
import com.higor.monolithicecommerce.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class UserResource(@Autowired val service: UserService){

    @GetMapping("/users")
    fun getAll(): ResponseEntity<List<User>> = ResponseEntity.ok(service.getAllUsers())

    @PostMapping("/users")
    fun create(@Valid @RequestBody userDTO: UserDTO): ResponseEntity<User> {
        val user: User = userDTO.toEntity()
        service.create(user)
        return ResponseEntity.ok(user)
    }
}