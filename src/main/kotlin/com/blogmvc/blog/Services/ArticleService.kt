package com.blogmvc.blog.Services

import com.blogmvc.blog.Model.Article
import com.blogmvc.blog.Model.Author
import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Repository.ArticleRepository
import com.blogmvc.blog.Repository.AuthorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ArticleService(private val articleRepository: ArticleRepository, private val authorRepository: AuthorRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    fun save(article: Article, currentUser: User): Article {
        logger.info("Article Save Service...")
        val authorOptional: Optional<Author> = authorRepository.findByUserId(currentUser.id)
        val author = if (authorOptional.isPresent) {
            authorOptional.get()
        } else {
            val author = Author(user = currentUser)
            authorRepository.save(author).also { logger.info("Autor criado com sucesso") }
        }

        article.author = author
        article.date = LocalDateTime.now()
        return articleRepository.save(article)
    }

    fun findAll(page: Int): Page<Article> {
        val pageRequest = PageRequest.of(page,1, Sort.by(Sort.Direction.DESC, "id"))
        return articleRepository.findAll(pageRequest)
    }

    fun findByAuthorUserId(idUser: Long, sortUserArticles: Sort): List<Article> {
        return articleRepository.findByAuthorUserId(idUser, sortUserArticles)
    }

    fun findByCategoryId(idCategory: Long, sortCategoryArticles: Sort): List<Article> {
        return articleRepository.findByCategoryId(idCategory, sortCategoryArticles)
    }

    fun findById(idArticle: Long): Article {
        return articleRepository.findById(idArticle).get()
    }

    fun deleteById(idArticle: Long) {
        return articleRepository.deleteById(idArticle)
    }

    fun findAllPage(): List<Article> {
        return articleRepository.findAll()
    }
}