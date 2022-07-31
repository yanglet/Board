package com.example.board.domain.post.repository

import com.example.board.domain.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>, PostRepositoryCustom {
}