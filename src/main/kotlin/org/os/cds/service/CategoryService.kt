package org.os.cds.service

import org.os.cds.dto.CategoryDto
import java.util.*

interface CategoryService {
    fun createCategory(categoryDto: CategoryDto): CategoryDto
    fun getCategoryById(id: UUID): CategoryDto
    fun updateCategory(id: UUID, categoryDto: CategoryDto): CategoryDto
    fun deleteCategory(id: UUID)
    fun getAllCategories(): List<CategoryDto>
}