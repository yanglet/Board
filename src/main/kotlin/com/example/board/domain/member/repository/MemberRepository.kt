package com.example.board.domain.member.repository

import com.example.board.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String?): Member;
    fun existsByEmail(email: String?): Boolean;
}