package com.example.board.domain.post.repository

import com.example.board.domain.post.entity.Post

interface PostRepositoryCustom {
    fun findAllFetch(): List<Post>;
    fun findByIdFetch(id: Long): Post?;
}