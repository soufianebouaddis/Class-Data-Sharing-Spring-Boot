package org.os.cds.controller

import org.os.cds.dto.ProductDto
import org.os.cds.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/product/")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("create-product")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        val createdProduct = productService.createProduct(productDto)
        return ResponseEntity.ok(createdProduct)
    }

    @GetMapping("get-product/{id}")
    fun getProductById(@PathVariable id: UUID): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @PutMapping("update-product/{id}")
    fun updateProduct(
        @PathVariable id: UUID,
        @RequestBody productDto: ProductDto
    ): ResponseEntity<ProductDto> {
        val updatedProduct = productService.updateProduct(id, productDto)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("delete-product/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("products")
    fun getAllProducts(): ResponseEntity<List<ProductDto>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products)
    }
}
