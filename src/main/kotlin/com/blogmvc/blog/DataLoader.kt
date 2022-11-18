package com.blogmvc.blog

import com.blogmvc.blog.Model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader(private val userRepository: UserRepository): CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (userRepository.count() == 0L) {
            val user = User(
                name = "Admin",
                email = "admin@hotmail.com",
                password = "admin"
            )
            userRepository.save(user).also { logger.info(it.toString()) }
        }
    }
}