package com.example.board.global.security.jwt.exception

import io.jsonwebtoken.JwtException

class TokenHasExpiredException : JwtException("토큰이 만료되었습니다.")