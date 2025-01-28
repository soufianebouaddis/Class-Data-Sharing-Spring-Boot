package org.os.cds.controller

import org.os.cds.dto.CategoryDto
import org.os.cds.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/category/")
class CategoryController (
    private val categoryService: CategoryService
    )
    {

        @PostMapping("create-category")
        fun createCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
            val createdCategory = categoryService.createCategory(categoryDto)
            return ResponseEntity.ok(createdCategory)
        }

        @GetMapping("get-category/{id}")
        fun getCategoryById(@PathVariable id: UUID): ResponseEntity<CategoryDto> {
            val category = categoryService.getCategoryById(id)
            return ResponseEntity.ok(category)
        }

        @PutMapping("update-category/{id}")
        fun updateCategory(
            @PathVariable id: UUID,
            @RequestBody categoryDto: CategoryDto
        ): ResponseEntity<CategoryDto> {
            val updatedCategory = categoryService.updateCategory(id, categoryDto)
            return ResponseEntity.ok(updatedCategory)
        }

        @DeleteMapping("delete-category/{id}")
        fun deleteCategory(@PathVariable id: UUID): ResponseEntity<Void> {
            categoryService.deleteCategory(id)
            return ResponseEntity.noContent().build()
        }

        @GetMapping("categories")
        fun getAllCategories(): ResponseEntity<List<CategoryDto>> {
            val categories = categoryService.getAllCategories()
            return ResponseEntity.ok(categories)
        }
    }