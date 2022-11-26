package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.Article
import com.blogmvc.blog.Model.Author
import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Repositories.ArticleRepository
import com.blogmvc.blog.Repositories.AuthorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDateTime
import java.util.Optional
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/article")
class ArticleController(private val authorRepository: AuthorRepository, private val articleRepository: ArticleRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun articlePage(model: Model): String {
        logger.info("articlePage()...")
        model.addAttribute("article", Article())
        return "article"
    }

    @PostMapping
    fun saveArticle(article: Article, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        logger.info("saveArticle(${article})")
        val currentUser = session.getAttribute("currentUser") as User
        val authorOptional: Optional<Author> = authorRepository.findByUserId(currentUser.id)
        val author = if (authorOptional.isPresent) {
            authorOptional.get()
        } else {
            val author = Author(user = currentUser)
            authorRepository.save(author).also { logger.info("Autor criado com sucesso") }
        }

        article.author = author
        article.date = LocalDateTime.now()
        articleRepository.save(article)
        val messageSuccess = "Artigo criado com sucesso"
        logger.info(messageSuccess)
        redirectAttributes.addFlashAttribute("messageSuccess", messageSuccess) //Só vai existir no contexto e não na sessão

        return "redirect:/"
    }

    @GetMapping("/list")
    fun listAllArticles(): String {
        logger.info("listAllArticles()....")
        return "article-list"
    }
}