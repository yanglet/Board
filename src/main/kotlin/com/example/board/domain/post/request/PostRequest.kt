package com.example.board.domain.post.request

import javax.validation.constraints.NotEmpty

data class PostRequest(
    @NotEmpty
    val title: String,
    @NotEmpty
    val content: String,
)
