package com.higor.monolithicecommerce.resource.exception

import com.higor.monolithicecommerce.model.DTO.StandardError
import com.higor.monolithicecommerce.model.service.exception.ProductQuantityNotAllowed
import com.higor.monolithicecommerce.model.service.exception.ResourceAlreadyExists
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    val messageList: HashSet<String> = HashSet()

    @ExceptionHandler(ResourceAlreadyExists::class)
    fun resourceAlreadyExists(ex: ResourceAlreadyExists, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                StandardError(this.messageList, request.requestURI, Instant.now())
        )
    }

    @ExceptionHandler(ResourceNotFound::class)
    fun resourceNotFound(ex: ResourceNotFound, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                StandardError(this.messageList, request.requestURI, Instant.now())
        )
    }

    @ExceptionHandler(ProductQuantityNotAllowed::class)
    fun productQuantityNotAllowed(ex: ProductQuantityNotAllowed, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                StandardError(this.messageList, request.requestURI, Instant.now())
        )
    }
}