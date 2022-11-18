package com.blogmvc.blog.Controller

import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/login")
class LoginController(private val repository: UserRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun userLogin(model: Model): String {
        logger.info("userLogin()...")
        model.addAttribute("user", User())
        return "login"
    }

    @PostMapping
    fun login(user: User, model: Model, session: HttpSession): String {
        logger.info("login()...")

        val findUser = repository.findByEmail(user.email)
        val messageErrorLogin = "Senha ou E-mail inválido"

        if (findUser.isEmpty) {
            model.addAttribute("messageErrorLogin", messageErrorLogin)
            logger.error(messageErrorLogin)
            return "login"
        }

        val userDatabase = findUser.get()

        if (user.password != userDatabase.password) {
            model.addAttribute("messageErrorLogin", messageErrorLogin)
            logger.error(messageErrorLogin)
            return "login"
        }

        logger.info("Login executado com sucesso")
        session.setAttribute("currentUser", userDatabase)
        return "redirect:/"
    }

    @GetMapping("/logout")
    fun logout(session: HttpSession): String{
        session.invalidate()
        return "redirect:/login"
    }

}