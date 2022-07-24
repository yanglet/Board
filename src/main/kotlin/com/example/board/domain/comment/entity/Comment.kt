package com.example.board.domain.comment.entity

import com.example.board.domain.common.entity.BaseEntity
import com.example.board.domain.member.entity.Member
import com.example.board.domain.post.entity.Post
import javax.persistence.*

@Entity
class Comment(
        content: String,
        member: Member,
        post: Post
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long? = null;
    var content: String = content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post = post;
}