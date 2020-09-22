package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.higor.monolithicecommerce.utils.PasswordUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Embedded


@Document(collection = "users")
data class User(
        @Id
        @JsonIgnore
        val id: String? = null,
        val name: String,
        val email: String,
        @JsonIgnore
        var password: String,
        @Embedded
        val address: Address
){

        fun passwordEncrypt() {
                this.password = PasswordUtils.encrypt(this.password)
        }
}