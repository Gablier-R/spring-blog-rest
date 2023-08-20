package com.example.springblog.service;

import com.example.springblog.dto.PostDTO;
import com.example.springblog.dto.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePostById(long id);
}
