package com.example.board.domain.post.entity

import com.example.board.domain.comment.entity.Comment
import com.example.board.domain.common.entity.BaseEntity
import com.example.board.domain.member.entity.Member
import javax.persistence.*

@Entity
class Post(
        title: String,
        content: String,
        comments: List<Comment>,
        member: Member
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0;
    var title: String = title;
    var content: String = content;
    @OneToMany(mappedBy = "post")
    var comments: List<Comment> = comments;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member;

    fun change(title: String, content: String){
        this.title = title;
        this.content = content;
    }
}