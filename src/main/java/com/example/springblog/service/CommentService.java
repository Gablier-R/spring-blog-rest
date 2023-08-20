package com.example.springblog.service;

import com.example.springblog.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentByPostId(long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, long commentId, CommentDTO commentDTO);

    void deleteComment(Long postId, Long commentId);
}
