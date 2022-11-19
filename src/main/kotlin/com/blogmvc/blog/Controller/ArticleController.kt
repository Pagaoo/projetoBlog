package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.Article
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/article")
class ArticleController {

    @GetMapping
    fun articlePage(model: Model): String {
        model.addAttribute("article", Article())
        return "article"
    }
}