package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.DTO.CartDTO
import com.higor.monolithicecommerce.model.entity.Cart
import com.higor.monolithicecommerce.model.entity.Product
import com.higor.monolithicecommerce.model.repository.CartRepository
import com.higor.monolithicecommerce.model.service.exception.ProductQuantityNotAllowed
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartService(@Autowired val repository: CartRepository,
                  @Autowired val productService: ProductService,
                  @Autowired val userService: UserService) {

    fun getUserCart(userEmail: String): Cart {
        val user = this.userService.getUserByEmail(userEmail) ?: throw ResourceNotFound("User doesn't exists")
        val cart = repository.getByCustomer(user)
        return cart ?: throw ResourceNotFound("Cart not found for this user")
    }


    fun addProduct(cartDTO: CartDTO): Cart {
        val user = this.userService.getUserByEmail(cartDTO.userEmail) ?: throw ResourceNotFound("User doesn't exists")
        val productInDb = this.productService.getBySku(cartDTO.product.sku) ?: throw ResourceNotFound("Product doesn't exists")

        if (!productInDb.quantityIsAvailable(cartDTO.product.quantity)){
            throw ProductQuantityNotAllowed("Product quantity is invalid")
        }

        val cart = try {
            this.getUserCart(user.email)
        }catch(ex: ResourceNotFound){
            Cart(customer = user)
        }
        cart.voucher = cartDTO.voucher
        val productToAdd: Product = productInDb.copy(
                id = null,
                sku = productInDb.sku,
                name = productInDb.name,
                quantity = cartDTO.product.quantity,
                price = productInDb.price
        )
        val productInCart = this.getCartProductBySku(productToAdd.sku, cart)

        productInCart?.let {
            if(!productInDb.quantityIsAvailable(it.quantity.plus(productToAdd.quantity))){
                throw ProductQuantityNotAllowed("Product quantity is invalid")
            }

            it.quantity = it.quantity.plus(productToAdd.quantity)
            cart.calculateTotalPrice()
            return this.repository.save(cart)
        }

        cart.products.add(productToAdd)
        cart.calculateTotalPrice()
        return this.repository.save(cart)

    }

    fun getCartProductBySku(sku: String, cart: Cart): Product? = cart.products.firstOrNull {it.sku == sku }

    fun removeProduct(cartDTO: CartDTO): Cart {
        val user = this.userService.getUserByEmail(cartDTO.userEmail) ?: throw ResourceNotFound("User doesn't exists")
        val cart = this.getUserCart(user.email)
        val product = this.getCartProductBySku(cartDTO.product.sku, cart)

        product?.let {
            cart.products.remove(it)
            cart.calculateTotalPrice()
            this.repository.save(cart)
        }

        return cart
    }
}