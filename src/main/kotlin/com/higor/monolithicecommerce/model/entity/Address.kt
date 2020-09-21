package com.higor.monolithicecommerce.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

import javax.persistence.Embeddable


@Embeddable
data class Address (
        @Id
        @JsonIgnore
        val id: String? = null,
        val street: String,
        val number: Long,
        val neighborhood: String,
        val zipcode: String,
        val city: String,
        val state: String,
        val country: String
)