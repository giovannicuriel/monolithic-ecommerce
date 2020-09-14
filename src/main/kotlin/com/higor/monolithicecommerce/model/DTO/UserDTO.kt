package com.higor.monolithicecommerce.model.DTO

import com.higor.monolithicecommerce.model.entity.User
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Validated
data class UserDTO (
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        val name: String,
        val email: String,
        val password: String
) {
        fun toEntity(): User = User(name = this.name, email = this.email, password = this.password)
}