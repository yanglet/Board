package com.example.board.global.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class NotFoundException @JvmOverloads constructor(message: String? = "Not Found Exception") :
    ResponseStatusException(HttpStatus.NOT_FOUND, message)