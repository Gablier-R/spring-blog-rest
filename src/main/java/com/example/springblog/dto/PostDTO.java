package com.example.springblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotBlank
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotBlank
    private String content;
    private Set<CommentDTO> comments;

}
