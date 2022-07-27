package com.example.board.domain.member.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SignupRequest(
    @NotEmpty
    @Email
    val email: String?,
    @NotEmpty
    val password: String?,
    @NotEmpty
    val name: String?,
)
