package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignUpController {

    @GetMapping
    fun signup(): String {
        return "signup"
    }

    @PostMapping
    fun save(user: User, confirmPassword: String) : String {
        println(confirmPassword)
        return "redirect:/"
    }
}