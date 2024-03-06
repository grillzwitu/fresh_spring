package com.example.freshspring

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("api/v1/articles")
class ArticleController( val repository: ArticleRepository) {

    @GetMapping
    fun articles() = repository.findAllByOrderByCreatedAtDesc()

    @GetMapping("/{slug}")
    fun articles(@PathVariable slug: String): Article =
        repository.findBySlug(slug)?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    fun newArticle(@RequestBody article: Article): Article {
        repository.save(article)
        return  article
    }

    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String): Article{
        val existingArticle = repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        existingArticle.content = article.content
        return  article
    }

    @DeleteMapping("/{slug}")
    fun  deleteArticle(@PathVariable slug: String) {
        val existingArticle = repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        repository.delete(existingArticle)
    }

}