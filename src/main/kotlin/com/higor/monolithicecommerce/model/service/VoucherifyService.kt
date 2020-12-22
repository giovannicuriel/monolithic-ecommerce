package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidVoucherDTO
import com.higor.monolithicecommerce.model.DTO.Voucherify.ValidateVoucherDTO
import com.higor.monolithicecommerce.model.DTO.Voucherify.VoucherOrderDTO
import com.higor.monolithicecommerce.model.service.definition.VoucherifyCallable
import com.higor.monolithicecommerce.model.service.definition.VoucherifyEndpoint
import com.higor.monolithicecommerce.model.service.exception.ThirdPartyApiException
import com.higor.monolithicecommerce.utils.NetworkUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class VoucherifyService(@Autowired val env: Environment) : VoucherifyCallable {

    private val apiBaseURL: String = env.getProperty("voucherify.baseUrl")!!
    private val appId: String = env.getProperty("voucherify.appId")!!
    private val appToken: String = env.getProperty("voucherify.appToken")!!


    private val restClient = NetworkUtils.getRetrofitInstance(this.apiBaseURL).create(VoucherifyEndpoint::class.java)

    override fun validateVoucher(voucherCode: String, cartValue: Double): ValidVoucherDTO? {
        return try {
            val validateVoucher = ValidateVoucherDTO(VoucherOrderDTO(cartValue.toLong()))
            val callback = this.restClient.validateVoucher(voucherCode, this.appId, this.appToken, validateVoucher)
            val response = callback.execute()

            response.body()

        }catch (ex: IOException){
            throw ThirdPartyApiException("An error occurred while trying to connect a third party api")
        }
    }

}