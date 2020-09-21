package com.higor.monolithicecommerce.model.DTO

import java.time.Instant

data class StandardError(
        val messages: HashSet<String> = HashSet(),
        val path: String,
        val now: Instant
)
