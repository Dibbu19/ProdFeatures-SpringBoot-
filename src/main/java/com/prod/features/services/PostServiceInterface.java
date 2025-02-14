package com.prod.features.services;


import com.prod.features.dto.PostDto;

import java.util.List;

public interface PostServiceInterface {
    List<PostDto> getAllPosts();
    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long postId);
}
