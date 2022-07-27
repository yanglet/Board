package com.example.board.domain.member.controller

import com.example.board.domain.member.service.MemberService
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService? = null) {
}