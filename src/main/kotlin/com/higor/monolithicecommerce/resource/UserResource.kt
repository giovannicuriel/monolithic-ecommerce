package com.higor.monolithicecommerce.resource

import com.higor.monolithicecommerce.model.DTO.UserDTO
import com.higor.monolithicecommerce.model.entity.User
import com.higor.monolithicecommerce.model.service.UserService
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class UserResource(@Autowired val service: UserService){

    @GetMapping("/users")
    fun getAll(): ResponseEntity<List<User>> = ResponseEntity.ok(service.getAllUsers())

    @GetMapping("/user")
    fun getByEmail(@RequestParam email: String): ResponseEntity<User> {
        val user = service.getUserByEmail(email) ?: throw ResourceNotFound("Resource not found: $email")

        return ResponseEntity.ok(user)
    }

    @PostMapping("/users")
    fun create(@Valid @RequestBody userDTO: UserDTO): ResponseEntity<User> {
        val user = service.create(userDTO)
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .queryParam("email", user.email)
                        .build()
                        .toUri()
        ).body(user)
    }

    @DeleteMapping("/users")
    fun deleteByEmail(@RequestParam email: String): ResponseEntity<User> {
        service.deleteByEmail(email)

        return ResponseEntity.noContent().build()
    }
}