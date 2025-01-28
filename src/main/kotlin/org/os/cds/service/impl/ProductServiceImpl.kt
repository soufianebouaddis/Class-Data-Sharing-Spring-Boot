package org.os.cds.service.impl

import org.os.cds.dto.ProductDto
import org.os.cds.repository.CategoryRepository
import org.os.cds.repository.ProductRepository
import org.os.cds.service.ProductService
import org.os.cds.utils.ProductMapper
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val productMapper: ProductMapper
) : ProductService {

    override fun createProduct(productDto: ProductDto): ProductDto {
        validateCategory(productDto.categoryId)
        val product = productMapper.toEntity(productDto)
        return productMapper.toDto(productRepository.save(product))
    }

    override fun getProductById(id: UUID): ProductDto {
        val product = productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Product with ID $id not found") }
        return productMapper.toDto(product)
    }

    override fun updateProduct(id: UUID, productDto: ProductDto): ProductDto {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Product with ID $id not found") }

        val category = categoryRepository.findById(productDto.categoryId)
            .orElseThrow { IllegalArgumentException("Category with ID ${productDto.categoryId} not found") }

        existingProduct.let {
            it.name = productDto.name
            it.price = productDto.price
            it.reference = productDto.reference
            it.category = category
        }

        val savedProduct = productRepository.save(existingProduct)
        return productMapper.toDto(savedProduct)
    }

    override fun deleteProduct(id: UUID) {
        if (!productRepository.existsById(id)) {
            throw IllegalArgumentException("Product with ID $id not found")
        }
        productRepository.deleteById(id)
    }


    override fun getAllProducts(): List<ProductDto> {
        val products = productRepository.findAll()
        return products.map { productMapper.toDto(it) }
    }
    private fun validateCategory(categoryId: UUID) {
        if (!categoryRepository.existsById(categoryId)) {
            throw ClassNotFoundException("Category not found with id: $categoryId")
        }
    }
}
