package com.blogmvc.blog.Model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Category")
data class Category(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String = ""
)