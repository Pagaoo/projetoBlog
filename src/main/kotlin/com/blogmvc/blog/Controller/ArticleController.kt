package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.Article
import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Services.ArticleService
import com.blogmvc.blog.Services.CategoryService
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
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/article")
class ArticleController(private val categoryService: CategoryService, private val articleService: ArticleService) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun articlePage(model: Model): String {
        logger.info("articlePage()...")
        model.addAttribute("article", Article())
        model.addAttribute("categories", categoryService.findAll())
        return "article"
    }

    @PostMapping
    fun saveArticle(article: Article, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        logger.info("saveArticle(${article})")
        val currentUser = session.getAttribute("currentUser") as User
        articleService.save(article, currentUser)
        val messageSuccess = "Artigo criado com sucesso"
        redirectAttributes.addFlashAttribute("messageSuccess", messageSuccess) //Só vai existir no contexto e não na sessão
        logger.info(messageSuccess)

        return "redirect:/"
    }

    @GetMapping("/list")
    fun listAllArticles(model: Model): String {
        logger.info("listAllArticles()....")
        model.addAttribute("articles", articleService.findAllPage()) // aqui
        model.addAttribute("categories", categoryService.findAll())
        return "article-list"
    }
    @GetMapping("/list/user/{idUser}")
    fun listAllUserArticles(@PathVariable idUser: Long, model: Model): String {
        logger.info("listAllUserArticles(idUser = $idUser)....")
        val sortUserArticles = Sort.by(Sort.Direction.DESC, "id")
        model.addAttribute("articles", articleService.findByAuthorUserId(idUser, sortUserArticles))
        model.addAttribute("categories", categoryService.findAll())
        return "article-list"
    }

    @GetMapping("/list/category/{idCategory}")
    fun listCategoryArticles(@PathVariable idCategory: Long, model: Model): String {
        logger.info("listAllUsersArticles(idCategory = $idCategory)....")
        val sortCategoryArticles = Sort.by(Sort.Direction.DESC, "id")
        model.addAttribute("articles", articleService.findByCategoryId(idCategory, sortCategoryArticles))
        model.addAttribute("categories", categoryService.findAll())
        return "article-list"
    }

    @GetMapping("/edit/{idArticle}")
    fun articleEditPage(@PathVariable idArticle: Long, model: Model): String {
        logger.info("articleEditPage(id = ${idArticle})...")
        model.addAttribute("article", articleService.findById(idArticle))
        model.addAttribute("categories", categoryService.findAll())
        return "article"
    }

    @GetMapping("/delete/{idArticle}")
    fun articleDeletePage(@PathVariable idArticle: Long, model: Model): String {
        logger.info("articleDeletePage(id = ${idArticle})...")
        articleService.deleteById(idArticle)
        return "redirect:/article/list"
    }

}