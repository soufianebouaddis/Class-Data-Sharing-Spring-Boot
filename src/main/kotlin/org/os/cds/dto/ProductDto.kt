package org.os.cds.dto

import java.math.BigDecimal
import java.util.UUID

data class ProductDto(
    val name: String,
    val price: BigDecimal,
    val reference: String,
    val categoryId: UUID
)
