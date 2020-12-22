package com.higor.monolithicecommerce.model.service.definition

import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidVoucherDTO

interface VoucherifyCallable {

    fun validateVoucher(voucherCode: String, cartValue: Double): ValidVoucherDTO?

}