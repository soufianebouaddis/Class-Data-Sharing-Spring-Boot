package org.os.cds.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.math.BigDecimal
import java.util.UUID

@Entity
@AllArgsConstructor
@NoArgsConstructor
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: UUID,
    var name: String,
    var price: BigDecimal,
    var reference: String,
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category
)