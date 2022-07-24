package com.example.board.domain.member.entity

import com.example.board.domain.common.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(
        email: String,
        password: String,
        name: String,
        role: String
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null;
    var email: String = email;
    var password: String = password;
    var name: String = name;
    var role: String = role;
}