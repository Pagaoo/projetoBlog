package com.blogmvc.blog.Repository

import com.blogmvc.blog.Model.Article
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByAuthorUserId(idUser: Long, sort: Sort): List<Article>
    fun findByCategoryId(idCategory: Long, sort: Sort): List<Article>
}