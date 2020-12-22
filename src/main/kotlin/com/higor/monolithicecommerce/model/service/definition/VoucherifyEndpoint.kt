package com.higor.monolithicecommerce.model.service.definition

import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidateVoucherDTO
import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidVoucherDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.POST

interface VoucherifyEndpoint {

    @POST("vouchers/{voucherCode}/validate")
    fun validateVoucher(
        @Path("voucherCode") voucherCode: String,
        @Header("X-App-Id") appId: String,
        @Header("X-App-Token") appToken: String,
        @Body requestBody: ValidateVoucherDTO
    ): Call<ValidVoucherDTO>
}