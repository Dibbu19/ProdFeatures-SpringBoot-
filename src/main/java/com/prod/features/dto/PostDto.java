package com.prod.features.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String title;
    private String description;
}
