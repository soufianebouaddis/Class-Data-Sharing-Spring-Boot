package org.os.cds.service.impl

import org.os.cds.dto.CategoryDto
import org.os.cds.repository.CategoryRepository
import org.os.cds.service.CategoryService
import org.os.cds.utils.CategoryMapper
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) : CategoryService {

    override fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val category = categoryMapper.toEntity(categoryDto)
        val savedCategory = categoryRepository.save(category)
        return categoryMapper.toDto(savedCategory)
    }

    override fun getCategoryById(id: UUID): CategoryDto {
        val category = categoryRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Category with ID $id not found") }
        return categoryMapper.toDto(category)
    }

    override fun updateCategory(id: UUID, categoryDto: CategoryDto): CategoryDto {
        val existingCategory = categoryRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Category with ID $id not found") }
        existingCategory.name = categoryDto.name
        val savedCategory = categoryRepository.save(existingCategory)
        return categoryMapper.toDto(savedCategory)
    }

    override fun deleteCategory(id: UUID) {
        if (!categoryRepository.existsById(id)) {
            throw IllegalArgumentException("Category with ID $id not found")
        }
        categoryRepository.deleteById(id)
    }

    override fun getAllCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll()
        return categories.map { categoryMapper.toDto(it) }
    }
}
