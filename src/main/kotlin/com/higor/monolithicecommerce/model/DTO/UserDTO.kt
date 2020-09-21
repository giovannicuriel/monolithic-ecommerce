package com.higor.monolithicecommerce.model.DTO

import com.higor.monolithicecommerce.model.entity.Address
import com.higor.monolithicecommerce.model.entity.User
import org.springframework.validation.annotation.Validated


@Validated
data class UserDTO (
        val name: String,
        val email: String,
        val password: String,
        val street: String,
        val number: Long,
        val neighborhood: String,
        val zipcode: String,
        val city: String,
        val state: String,
        val country: String
) {
        fun toEntity(): User = User(
                name = this.name,
                email = this.email,
                password = this.password,
                address = Address(
                        street = this.street,
                        number = this.number,
                        neighborhood = this.neighborhood,
                        zipcode = this.zipcode,
                        city = this.city,
                        state = this.state,
                        country = this.country
                )
        )
}