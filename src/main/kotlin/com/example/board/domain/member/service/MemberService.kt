package com.example.board.domain.member.service

import com.example.board.domain.member.repository.MemberRepository
import com.example.board.global.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository? = null,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null,
    private val jwtProvider: JwtProvider? = null
) {
    fun signup(){

    }
}