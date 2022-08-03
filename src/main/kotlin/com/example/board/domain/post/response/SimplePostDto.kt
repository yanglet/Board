package com.example.board.domain.post.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class SimplePostDto(
    val id: Long,
    val title: String,
    val email: String, // 회원 이메일
    val name: String, // 회원 이름
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val createTime: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val modifiedTime: LocalDateTime,
)
