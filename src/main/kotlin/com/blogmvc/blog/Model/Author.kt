package com.blogmvc.blog.Model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "author")
data class Author(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var about: String = "",
    @OneToOne
    var user: User = User(),

    var facebook: String = "",
    var twitter: String = "",
    var linkedin: String = ""
)
