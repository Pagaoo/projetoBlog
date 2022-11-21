package com.blogmvc.blog.Controller

import com.blogmvc.blog.Repositories.ArticleRepository
import com.blogmvc.blog.Repositories.CategoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController(private val categoryRepository: CategoryRepository, private val articleRepository: ArticleRepository) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun index(model: Model): String {
        logger.info("index()...")
        model.addAttribute("categories",categoryRepository.findAll())
        model.addAttribute("articles", articleRepository.findAll())
        return "index"
    }
}