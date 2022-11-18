package com.blogmvc.blog.Repositories

import com.blogmvc.blog.Model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}