package org.os.cds.utils

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.os.cds.dto.CategoryDto
import org.os.cds.entity.Category

@Mapper(componentModel = "spring")
interface CategoryMapper {
    companion object {
        val INSTANCE: CategoryMapper = Mappers.getMapper(CategoryMapper::class.java)
    }

    fun toDto(category: Category): CategoryDto

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    fun toEntity(categoryDto: CategoryDto): Category
}