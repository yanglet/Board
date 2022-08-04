package com.example.board.domain.post.controller

import com.example.board.domain.member.entity.Member
import com.example.board.domain.post.request.PostRequest
import com.example.board.domain.post.response.PostDto
import com.example.board.domain.post.response.PostResponse
import com.example.board.domain.post.response.SimplePostDto
import com.example.board.domain.post.service.PostService
import com.example.board.global.security.annotation.LoginMember
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("board/v1/api/posts")
class PostController(
    private val postService: PostService
    ) {
    @GetMapping
    fun getPosts(): ResponseEntity<List<SimplePostDto>> {
        val posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/post")
    fun post(@Validated @RequestBody postRequest: PostRequest,
             @LoginMember member: Member?): ResponseEntity<PostResponse> {
        val post = postService.post(postRequest, member);
        return ResponseEntity(post, HttpStatus.CREATED);
    }

    @GetMapping("/{post_id}")
    fun getPost(@PathVariable("post_id") id: Long): ResponseEntity<PostDto> {
        val post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{post_id}")
    fun update(@PathVariable("post_id") id: Long,
               @Validated @RequestBody postRequest: PostRequest,
               @LoginMember member: Member?): ResponseEntity<PostResponse> {
        val post = postService.update(id, postRequest, member);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{post_id}")
    fun delete(@PathVariable("post_id") id: Long,
               @LoginMember member: Member?){
        postService.delete(id, member);
    }
}