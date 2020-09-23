package com.higor.monolithicecommerce.resource

import com.higor.monolithicecommerce.model.DTO.CartDTO
import com.higor.monolithicecommerce.model.entity.Cart

import com.higor.monolithicecommerce.model.service.CartService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/api")
class CartResource(@Autowired val service: CartService){

    @GetMapping("/cart")
    fun getUserCart(@RequestParam email: String): ResponseEntity<Cart> = ResponseEntity.ok(service.getUserCart(email))

    @PostMapping("/cart/add")
    fun addProduct(@RequestBody cartDTO: CartDTO): ResponseEntity<Cart>{
        return ResponseEntity.ok(this.service.addProduct(cartDTO))
    }

    @DeleteMapping("/cart/remove")
    fun removeProduct(@RequestBody cartDTO: CartDTO): ResponseEntity<Cart>{
        return ResponseEntity.ok(this.service.removeProduct(cartDTO))
    }
}