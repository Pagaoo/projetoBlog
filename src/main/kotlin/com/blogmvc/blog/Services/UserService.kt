package com.blogmvc.blog.Services

import com.blogmvc.blog.Model.User
import com.blogmvc.blog.Repository.UserRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

}