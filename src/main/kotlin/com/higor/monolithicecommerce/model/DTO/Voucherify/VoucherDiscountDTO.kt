package com.higor.monolithicecommerce.model.DTO.Voucherify

import com.google.gson.annotations.SerializedName

data class VoucherDiscountDTO(
    val type: String,
    @SerializedName("amount_off")
    val amountOff: Long? = null,
    @SerializedName("percent_off")
    val percentOff: Double? = null
)
