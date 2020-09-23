package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.entity.Order
import com.higor.monolithicecommerce.model.repository.OrderRepository
import com.higor.monolithicecommerce.model.service.exception.ProductQuantityNotAllowed
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import com.higor.monolithicecommerce.model.service.exception.UnableToCreateOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired val repository: OrderRepository,
                   @Autowired val cartService: CartService) {

    fun createOrder(userEmail: String): Order {
        val user = this.cartService.userService.getUserByEmail(userEmail) ?: throw ResourceNotFound("User doesn't exists")
        val cart = this.cartService.getUserCart(user.email)
        if (cart.products.isEmpty()){
            throw UnableToCreateOrder("Your cart must have at least 1 product")
        }
        cart.products.forEach {cartProduct ->
            val product = this.cartService.productService.getBySku(cartProduct.sku)

            product?.let{
                if(!it.quantityIsAvailable(cartProduct.quantity)){
                    throw ProductQuantityNotAllowed("Product quantity is invalid")
                }
                it.quantity -= cartProduct.quantity
                this.cartService.productService.repository.save(it)
            }
        }
        val order = Order(null, user, cart.products, cart.totalPrice)
        return this.repository.save(order)
    }
}