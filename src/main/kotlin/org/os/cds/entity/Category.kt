package org.os.cds.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: UUID,
    var name: String,
    @OneToMany(mappedBy = "category")
    val products: List<Product> = emptyList()
)