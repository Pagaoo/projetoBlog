package com.blogmvc.blog

import com.blogmvc.blog.Model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}