package com.prod.features.services;

import com.prod.features.dto.PostDto;
import com.prod.features.entities.PostEntity;
import com.prod.features.exceptions.ResourceNotFoundException;
import com.prod.features.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements PostServiceInterface {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDto> getAllPosts() {

        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id "+postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id "+postId));
        postDto.setPostId(postId);
        modelMapper.map(postDto, postEntity);
        postEntity = postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDto.class);
    }
}
