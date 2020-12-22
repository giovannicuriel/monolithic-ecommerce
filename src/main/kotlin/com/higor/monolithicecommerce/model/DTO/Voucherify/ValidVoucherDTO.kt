package com.higor.monolithicecommerce.model.DTO.Voucherify

data class ValidVoucherDTO(
    val code: String,
    val valid: Boolean,
    val discount: VoucherDiscountDTO? = null
)
