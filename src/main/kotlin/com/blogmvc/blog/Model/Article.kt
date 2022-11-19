package com.blogmvc.blog.Model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "articles")
data class Article(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var title: String = "",
    var subTitle: String = "",
    var content: String = "",
    var date: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    var author: Author = Author()
)