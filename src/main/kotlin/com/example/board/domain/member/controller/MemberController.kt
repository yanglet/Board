package com.example.board.domain.member.controller

import com.example.board.domain.member.request.SigninRequest
import com.example.board.domain.member.request.SignupRequest
import com.example.board.domain.member.response.SigninResponse
import com.example.board.domain.member.response.SignupResponse
import com.example.board.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("board/v1/api/members")
class MemberController(private val memberService: MemberService) {
    @PostMapping("/signup")
    fun signup(@Validated @RequestBody signupRequest: SignupRequest): ResponseEntity<SignupResponse> {
        val signup = memberService.signup(signupRequest);
        return ResponseEntity(signup, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    fun signin(@Validated @RequestBody signinRequest: SigninRequest): ResponseEntity<SigninResponse> {
        val signin = memberService.signin(signinRequest);
        return ResponseEntity.ok(signin);
    }
}