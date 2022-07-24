package com.example.board.domain.common.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    var createTime: LocalDateTime = LocalDateTime.now();

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedTime: LocalDateTime = LocalDateTime.now();
}