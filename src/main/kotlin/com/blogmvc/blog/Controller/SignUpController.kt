package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.User
import com.blogmvc.blog.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignUpController(private val repository: UserRepository) {

    @GetMapping
    fun signup(): String {
        return "signup"
    }

    @PostMapping
    fun save(user: User, confirmPassword: String, model: Model) : String {
        println(confirmPassword)

        if(user.password != confirmPassword) {
            model.addAttribute("messageError", "As senhas não são iguais")
            return "signup"
        }

        repository.save(user).also(::println)
        return "redirect:/"
    }
}