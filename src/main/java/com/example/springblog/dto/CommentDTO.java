package com.example.springblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Name should not be null or empty")
    private String name;

    @NotBlank(message = "Email should not be null or empty")
    @Email
    private String email;

    @NotBlank
    @Size(min = 10 ,message = "Body should not be null or empty")
    private String body;

}
