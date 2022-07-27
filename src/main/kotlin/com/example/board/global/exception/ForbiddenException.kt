package com.example.board.global.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class ForbiddenException @JvmOverloads constructor(message: String? = "ForbiddenException") :
    ResponseStatusException(HttpStatus.FORBIDDEN, message)