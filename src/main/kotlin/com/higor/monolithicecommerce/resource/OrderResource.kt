package com.higor.monolithicecommerce.resource

import com.higor.monolithicecommerce.model.DTO.OrderDTO
import com.higor.monolithicecommerce.model.entity.Order
import com.higor.monolithicecommerce.model.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class OrderResource(@Autowired val service: OrderService) {

    @PostMapping("/order/create")
    fun createOrder(@RequestBody orderDTO: OrderDTO): ResponseEntity<Order>{
        return ResponseEntity.ok(this.service.publishOrder(orderDTO.userEmail))
    }
}