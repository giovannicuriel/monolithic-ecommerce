package com.higor.monolithicecommerce.model.service

import com.higor.monolithicecommerce.model.DTO.ProductDTO
import com.higor.monolithicecommerce.model.entity.Product
import com.higor.monolithicecommerce.model.repository.ProductRepository
import com.higor.monolithicecommerce.model.service.exception.ResourceAlreadyExists
import com.higor.monolithicecommerce.model.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val repository: ProductRepository) {

    fun getAll(): List<Product> = repository.findAll()

    fun getBySku(sku: String): Product? = repository.findBySku(sku)

    private fun productExists(sku: String): Boolean = this.getBySku(sku) != null

    fun create(productDTO: ProductDTO): Product {
        if (this.productExists(productDTO.sku)){
            throw ResourceAlreadyExists("Produto ja existente com esse SKU")
        }

        val product = productDTO.toEntity()
        return repository.save(product)
    }

    fun deleteBySku(sku: String) {
        if(!this.productExists(sku)){
            throw ResourceNotFound("Resource Not Found $sku")
        }
        val product = this.getBySku(sku)
        repository.delete(product!!)
    }
}