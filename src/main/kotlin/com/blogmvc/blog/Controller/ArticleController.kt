package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.Article
import com.blogmvc.blog.Model.Author
import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Repository.ArticleRepository
import com.blogmvc.blog.Repository.AuthorRepository
import com.blogmvc.blog.Repository.CategoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDateTime
import java.util.Optional
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/article")
class ArticleController(private val authorRepository: AuthorRepository, private val articleRepository: ArticleRepository, private val categoryRepository: CategoryRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun articlePage(model: Model): String {
        logger.info("articlePage()...")
        model.addAttribute("article", Article())
        model.addAttribute("categories", categoryRepository.findAll())
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
    fun listAllArticles(model: Model): String {
        logger.info("listAllArticles()....")
        model.addAttribute("articles", articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")))
        model.addAttribute("categories", categoryRepository.findAll())
        return "article-list"
    }
    @GetMapping("/list/user/{idUser}")
    fun listAllUserArticles(@PathVariable idUser: Long, model: Model): String {
        logger.info("listAllUserArticles(idUser = $idUser)....")
        val sortUserArticles = Sort.by(Sort.Direction.DESC, "id")
        model.addAttribute("articles", articleRepository.findByAuthorUserId(idUser, sortUserArticles))
        model.addAttribute("categories", categoryRepository.findAll())
        return "article-list"
    }

    @GetMapping("/list/category/{idCategory}")
    fun listCategoryArticles(@PathVariable idCategory: Long, model: Model): String {
        logger.info("listAllUserArticles(idUser = $idCategory)....")
        val sortCategoryArticles = Sort.by(Sort.Direction.DESC, "id")
        model.addAttribute("articles", articleRepository.findByCategoryId(idCategory, sortCategoryArticles))
        model.addAttribute("categories", categoryRepository.findAll())
        return "article-list"
    }

    @GetMapping("/edit/{idArticle}")
    fun articleEditPage(@PathVariable idArticle: Long, model: Model): String {
        logger.info("articleEditPage(id = ${idArticle})...")
        model.addAttribute("article", articleRepository.findById(idArticle).get())
        model.addAttribute("categories", categoryRepository.findAll())
        return "article"
    }

    @GetMapping("/delete/{idArticle}")
    fun articleDeletePage(@PathVariable idArticle: Long, model: Model): String {
        logger.info("articleDeletePage(id = ${idArticle})...")
        articleRepository.deleteById(idArticle)
        return "redirect:/article/list"
    }

}