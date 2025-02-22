package com.prod.features.controllers;

import com.prod.features.dto.PostDto;
import com.prod.features.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {
        return postService.updatePost(postDto,postId);
    }
}
