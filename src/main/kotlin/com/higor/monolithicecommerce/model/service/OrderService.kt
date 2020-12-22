package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.config.QueueManager
import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidVoucherDTO
import com.higor.monolithicecommerce.model.entity.Cart
import com.higor.monolithicecommerce.model.entity.Order
import com.higor.monolithicecommerce.model.entity.Product
import com.higor.monolithicecommerce.model.service.exception.ProductQuantityNotAllowed
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import com.higor.monolithicecommerce.model.service.exception.UnableToCreateOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired val cartService: CartService, @Autowired val voucherifyService: VoucherifyService) {

    fun publishOrder(userEmail: String): Order {
        val user = this.cartService.userService.getUserByEmail(userEmail) ?: throw ResourceNotFound("User doesn't exists")
        val cart = this.cartService.getUserCart(user.email)

        if (cart.products.isEmpty()){
            throw UnableToCreateOrder("Your cart must have at least 1 product")
        }

        this.validateCart(cart)
        this.validateVoucher(cart)
        val order = Order.build(user, cart.products, cart.totalPrice)
        QueueManager.publish(order.toJson()!!)
        return order
    }

    private fun validateCart(cart: Cart) {
        cart.products.forEach { cartProduct: Product ->
            val product = this.cartService.productService.getBySku(cartProduct.sku)

            product?.let { it: Product ->
                if (!it.quantityIsAvailable(cartProduct.quantity)) {
                    throw ProductQuantityNotAllowed("Product quantity is invalid")
                }
                it.quantity -= cartProduct.quantity
                this.cartService.productService.repository.save(it)
            }
        }
    }

    private fun validateVoucher(cart: Cart) {
        cart.voucher.let {
            val discountVoucher = this.voucherifyService.validateVoucher(it!!, cart.totalPrice)

            if (discountVoucher != null) {
                cart.totalPrice = when (discountVoucher.discount!!.type) {
                    "PERCENT" -> this.givePercentDiscount(cart, discountVoucher)
                    else -> this.giveAmountDiscount(cart, discountVoucher)
                }

            }
        }
    }

    private fun redeemVoucher(){
        TODO()
    }
    private fun givePercentDiscount(cart: Cart, discountVoucher: ValidVoucherDTO): Double =
        cart.totalPrice - (cart.totalPrice * discountVoucher.discount!!.percentOff!!) / 100

    private fun giveAmountDiscount(cart: Cart, discountVoucher: ValidVoucherDTO): Double =
        cart.totalPrice - discountVoucher.discount!!.amountOff!!

}