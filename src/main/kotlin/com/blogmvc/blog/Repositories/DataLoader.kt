package com.blogmvc.blog.Repositories

import com.blogmvc.blog.Model.Article
import com.blogmvc.blog.Model.Author
import com.blogmvc.blog.Model.Category
import com.blogmvc.blog.Model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class DataLoader(private val userRepository: UserRepository,
                 private val categoryRepository: CategoryRepository,
                 private val articleRepository: ArticleRepository,
                 private val authorRepository: AuthorRepository
): CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        loadUser()
        loadCategories()
        loadArticle()
    }

    private fun loadCategories() {
        if (categoryRepository.count() == 0L) {
            listOf(
                Category(name = "World"),
                Category(name = "U.S"),
                Category(name = "Technology"),
                Category(name = "Design"),
                Category(name = "Culture"),
                Category(name = "Business"),
                Category(name = "Politics"),
                Category(name = "Opinion"),
                Category(name = "Science"),
                Category(name = "Health"),
                Category(name = "Style"),
                Category(name = "Travel")
            ).also { categoryRepository.saveAll(it) }
        }
    }

    private fun loadUser() {
        if (userRepository.count() == 0L) {
           listOf(
               User(
                   name = "Admin",
                   email = "admin@hotmail.com",
                   password = "admin"
               ),
               User(
                   name = "Gabriel",
                   email = "gabi@hotmail.com",
                   password = "123"
               )
           ).also { userRepository.saveAll(it) }
        }
    }

    private fun loadArticle() {
        if (articleRepository.count() == 0L) {
            val categoryWorld = categoryRepository.findAll().get(0)
            val categoryTech = categoryRepository.findAll().get(2)
            val authors = authorRepository.saveAll(
                listOf(
                    Author(
                        user = userRepository.findAll().get(0),
                        about = "Sadosjadosijoadsijoad",
                        facebook = "http://facebook.com/admin",
                        twitter = "http://twitter.com/admin",
                        linkedin = "http://linkedin.com/admin"

                    ), Author(
                        user = userRepository.findAll().get(1),
                        about = "asdkadskopadskdaops",
                        facebook = "http://facebook.com/gabriel",
                        twitter = "http://twitter.com/gabriel",
                        linkedin = "http://linkedin.com/gabriel"
                    )
                )
            )
            listOf(
                Article(
                    title = "Lorem ipsum dolor sit amet, consectetur adipiscing",
                    subTitle = "elit, sed do eiusmod",
                    content = "Faucibus ornare suspendisse sed nisi. Gravida quis blandit turpis cursus. Vulputate mi sit amet mauris commodo quis imperdiet massa. Risus sed vulputate odio ut enim blandit. Quis blandit turpis cursus in hac habitasse platea dictumst.",
                    date = LocalDateTime.now(),
                    author = authors.get(0),
                    category = categoryTech
                ),
                Article(
                    title = "Pharetra convallis",
                    subTitle = "pharetra vel",
                    content = "Faucibus ornare suspendisse sed nisi. Gravida quis blandit turpis cursus. Vulputate mi sit amet mauris commodo quis imperdiet massa. Risus sed vulputate odio ut enim blandit.",
                    date = LocalDateTime.now(),
                    author = authors.get(1),
                    category = categoryTech
                ),
                Article(
                    title = "convallis convallis",
                    subTitle = "Consectetur",
                    content = "Nibh tellus molestie nunc non blandit massa enim. Ipsum dolor sit amet consectetur.",
                    date = LocalDateTime.now(),
                    author = authors.get(0),
                    category = categoryWorld
                )
            ).also { articleRepository.saveAll(it) }
        }
    }
}