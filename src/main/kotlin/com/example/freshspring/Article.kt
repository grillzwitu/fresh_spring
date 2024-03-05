package com.example.freshspring

import java.time.LocalDateTime

data class Article(
    var title: String,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var slug: String = title.toSlug()
)
