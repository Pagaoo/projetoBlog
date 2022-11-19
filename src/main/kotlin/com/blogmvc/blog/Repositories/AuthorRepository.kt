package com.blogmvc.blog.Repositories

import com.blogmvc.blog.Model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: JpaRepository<Author, Long>