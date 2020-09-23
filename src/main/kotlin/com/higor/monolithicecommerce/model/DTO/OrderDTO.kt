package com.higor.monolithicecommerce.model.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderDTO (
    @JsonProperty("user_email")
    val userEmail: String
)
