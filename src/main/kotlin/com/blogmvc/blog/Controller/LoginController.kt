package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun userLogin(model: Model): String {
        logger.info("userLogin()...")
        model.addAttribute("user", User())
        return "login"
    }
}