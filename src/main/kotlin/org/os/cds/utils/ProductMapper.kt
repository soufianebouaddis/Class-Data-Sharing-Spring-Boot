package org.os.cds.utils

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

import org.os.cds.dto.ProductDto
import org.os.cds.entity.Category
import org.os.cds.entity.Product
import org.os.cds.exception.NotFound
import org.os.cds.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    abstract fun toEntity(productDto: ProductDto): Product

    @Mapping(source = "category.id", target = "categoryId")
    abstract fun toDto(product: Product): ProductDto

    @Autowired
    protected lateinit var categoryRepository: CategoryRepository

    fun mapToCategory(categoryId: UUID): Category {
        return categoryRepository.findById(categoryId)
            .orElseThrow { NotFound("Category not found with id: $categoryId") }
    }
}