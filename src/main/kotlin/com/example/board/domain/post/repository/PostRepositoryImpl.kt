package com.example.board.domain.post.repository

import com.example.board.domain.comment.entity.QComment
import com.example.board.domain.comment.entity.QComment.*
import com.example.board.domain.member.entity.QMember
import com.example.board.domain.member.entity.QMember.*
import com.example.board.domain.post.entity.Post
import com.example.board.domain.post.entity.QPost.post
import com.querydsl.jpa.impl.JPAQueryFactory

class PostRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): PostRepositoryCustom {
    override fun findAllFetch(): List<Post> {
        return queryFactory.selectFrom(post)
            .leftJoin(post.member, member).fetchJoin()
            .orderBy(post.id.desc())
            .fetch();
    }

    override fun findByIdFetch(id: Long): Post? {
        return queryFactory.selectFrom(post)
            .leftJoin(post.member, member).fetchJoin()
            .leftJoin(post.comments, comment).fetchJoin()
            .leftJoin(comment.member, member).fetchJoin()
            .where(post.id.eq(id))
            .fetchOne();
    }
}