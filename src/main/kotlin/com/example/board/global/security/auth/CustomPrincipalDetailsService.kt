package com.example.board.global.security.auth

import com.example.board.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomPrincipalDetailsService(
    private val memberRepository: MemberRepository
    ): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return PrincipalDetails(memberRepository.findByEmail(username));
    }
}