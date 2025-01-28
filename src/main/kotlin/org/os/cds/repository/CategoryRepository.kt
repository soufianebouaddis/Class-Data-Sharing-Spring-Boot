package org.os.cds.repository

import org.os.cds.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category,UUID>{
}