package com.example.board.domain.post.service

import com.example.board.domain.member.entity.Member
import com.example.board.domain.post.entity.Post
import com.example.board.domain.post.repository.PostRepository
import com.example.board.domain.post.request.PostRequest
import com.example.board.domain.post.response.PostDto
import com.example.board.domain.post.response.PostResponse
import com.example.board.domain.post.response.SimplePostDto
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(private val postRepository: PostRepository) {
    @Transactional
    fun getPosts(): List<SimplePostDto> {
        return postRepository.findAllFetch()
            .map { p -> SimplePostDto(p.id, p.title, p.member.email, p.member.name, p.createTime, p.modifiedTime) }
            .toList()
    }

    @Transactional
    fun getPost(id: Long): PostDto {
        val post = postRepository.findByIdFetch(id) ?: throw IllegalArgumentException();
        return PostDto(post.id, post.title, post.content, post.member.email, post.member.name, post.createTime, post.modifiedTime);
    }

    @Transactional
    fun post(postRequest: PostRequest, member: Member?): PostResponse{
        if (member == null) {
            throw AccessDeniedException("access denied");
        }
        val post = Post(postRequest.title, postRequest.content, listOf(), member!!);
        val savePost = postRepository.save(post);

        return PostResponse(savePost.id);
    }

    @Transactional
    fun update(id: Long, postRequest: PostRequest, member: Member?): PostResponse {
        val post = postRepository.findById(id).orElseThrow();

        if (member == null) {
            throw AccessDeniedException("access denied");
        }

        if (post.member.id != member.id) {
            throw AccessDeniedException("access denied");
        }

        post.change(postRequest.title, postRequest.content);

        return PostResponse(post.id);
    }

    @Transactional
    fun delete(id: Long, member: Member?){
        val post = postRepository.findById(id).orElseThrow();

        if (member == null) {
            throw AccessDeniedException("access denied");
        }

        if (post.member.id != member.id) {
            throw AccessDeniedException("access denied");
        }

        postRepository.delete(post);
    }
}