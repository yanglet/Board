package com.example.board.global.security.jwt.exception

import com.example.board.global.exception.ForbiddenException

class TokenIsInvalidException : ForbiddenException("Token is invalid")