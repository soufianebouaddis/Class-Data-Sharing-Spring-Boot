package org.os.cds.repository

import org.os.cds.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product,UUID> {
}