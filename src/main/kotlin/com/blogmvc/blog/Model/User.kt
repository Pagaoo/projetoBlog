package com.blogmvc.blog.Model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    var id: Long = 0,
    val name: String,
    val email: String,
    val password: String
 )