package com.example.board.domain.member.service

import com.example.board.domain.member.entity.Member
import com.example.board.domain.member.exception.MemberDuplicateException
import com.example.board.domain.member.exception.PasswordNotMatchException
import com.example.board.domain.member.repository.MemberRepository
import com.example.board.domain.member.request.SigninRequest
import com.example.board.domain.member.request.SignupRequest
import com.example.board.domain.member.response.SigninResponse
import com.example.board.domain.member.response.SignupResponse
import com.example.board.global.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun signup(signUpRequest: SignupRequest): SignupResponse {
        if ( memberRepository.existsByEmail(signUpRequest.email) ) {
            throw MemberDuplicateException()
        }

        val encodePw = bCryptPasswordEncoder.encode(signUpRequest.password!!)

        val member = Member(signUpRequest.email!!,
            encodePw,
            signUpRequest.name!!,
            "ROLE_USER");

        val saveMember = memberRepository.save(member);

        return SignupResponse(saveMember.id!!);
    }

    fun signin(signinRequest: SigninRequest): SigninResponse {
        val member = memberRepository.findByEmail(signinRequest.email);

        checkPassword(signinRequest.password, member.password);

        val accessToken = jwtProvider.generateAccessToken(member);

        return SigninResponse(accessToken!!);
    }

    private fun checkPassword(loginPassword: String?, password: String?) {
        if ( !bCryptPasswordEncoder.matches(loginPassword, password) ) {
            throw PasswordNotMatchException();
        }
    }
}