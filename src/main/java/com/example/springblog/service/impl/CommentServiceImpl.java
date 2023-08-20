package com.example.springblog.service.impl;

import com.example.springblog.dto.CommentDTO;
import com.example.springblog.entity.Comment;
import com.example.springblog.entity.Post;
import com.example.springblog.exception.BlogAPIException;
import com.example.springblog.exception.NotFoundException;
import com.example.springblog.repository.CommentRepository;
import com.example.springblog.repository.PostRepository;
import com.example.springblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);

        Post post   = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newCommente =  commentRepository.save(comment);

        return mapToDTO(newCommente);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {

        Post post   = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post", "id", postId));

        Comment comment   = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, long commentId, CommentDTO commentRequest) {

        Post post   = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post", "id", postId));

        Comment comment   = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updateComment = commentRepository.save(comment);
        return mapToDTO(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Post post   = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post", "id", postId));

        Comment comment   = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment", "id", commentId));


        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }

    private CommentDTO mapToDTO(Comment comment){

        CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);

//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setId(comment.getId());
//        commentDTO.setName(comment.getName());
//        commentDTO.setEmail(comment.getEmail());
//        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    private Comment mapToEntity(CommentDTO commentDTO){

        Comment comment = mapper.map(commentDTO, Comment.class);

//        Comment comment = new Comment();
//        comment.setId(commentDTO.getId());
//        comment.setName(commentDTO.getName());
//        comment.setEmail(commentDTO.getEmail());
//        comment.setBody(commentDTO.getBody());

        return comment;
    }
}
