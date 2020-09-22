package com.higor.monolithicecommerce.resource

import com.higor.monolithicecommerce.model.DTO.ProductDTO
import com.higor.monolithicecommerce.model.DTO.UserDTO
import com.higor.monolithicecommerce.model.entity.Product
import com.higor.monolithicecommerce.model.entity.User
import com.higor.monolithicecommerce.model.service.ProductService
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ProductResource(@Autowired val service: ProductService) {

    @GetMapping("/products")
    fun getAll(): ResponseEntity<List<Product>> = ResponseEntity.ok(service.getAll())

    @GetMapping("/product")
    fun getBySku(@RequestParam sku: String): ResponseEntity<Product> {
        val product = service.getBySku(sku) ?: throw ResourceNotFound("Resource not found: $sku")

        return ResponseEntity.ok(product)
    }

    @PostMapping("/products")
    fun create(@Valid @RequestBody productDTO: ProductDTO): ResponseEntity<Product> {
        val product = service.create(productDTO)
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .queryParam("sku", product.sku)
                        .build()
                        .toUri()
        ).body(product)
    }

    @DeleteMapping("/products")
    fun deleteBySku(@RequestParam sku: String): ResponseEntity<Product> {
        service.deleteBySku(sku)
        return ResponseEntity.noContent().build()
    }
}