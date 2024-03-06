package com.example.freshspring

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
    /**
     * The Article repository Interface.
     */
    fun findAllByOrderByCreatedAtDesc(): Iterable<Article>
}