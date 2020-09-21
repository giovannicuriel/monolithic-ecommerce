package com.higor.monolithicecommerce.model.service.exception

class ResourceNotFound(message: String): Exception(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}