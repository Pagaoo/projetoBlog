package com.blogmvc.blog.Services

import com.blogmvc.blog.Model.Category
import com.blogmvc.blog.Repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

}