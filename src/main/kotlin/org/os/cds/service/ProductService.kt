package org.os.cds.service

import org.os.cds.dto.ProductDto
import java.util.*

interface ProductService {
    fun createProduct(productDto: ProductDto): ProductDto
    fun getProductById(id: UUID): ProductDto
    fun updateProduct(id: UUID, productDto: ProductDto): ProductDto
    fun deleteProduct(id: UUID)
    fun getAllProducts(): List<ProductDto>
}