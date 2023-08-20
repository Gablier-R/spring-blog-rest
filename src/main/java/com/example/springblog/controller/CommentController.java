package com.example.springblog.controller;

import com.example.springblog.dto.CommentDTO;
import com.example.springblog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDTO commentDTO){

        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentByPostId(postId);
    }

    @GetMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){

        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);

    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long postId, @PathVariable Long commentId,@Valid @RequestBody CommentDTO commentDTO){
        CommentDTO updateComment = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<String> deleteComment (@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
