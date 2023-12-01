package com.company.blogplatform.controller.comment;

import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.model.comments.Comment;
import com.company.blogplatform.service.comment.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all-comments")
    public Page<Comment> getAllComments(@RequestParam(defaultValue = "0") Integer pageNumber,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sort) {
        return commentService.getAllComments(pageNumber, pageSize, sort);
    }

    @GetMapping("/comment-by-id")
    public Comment getCommentById(@RequestParam Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/comments-by-post-id")
    public Page<Comment> getCommentsByPostId(@RequestParam Long postId,
                                             @RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sort) {
        return commentService.getCommentsByPostId(postId, pageNumber, pageSize, sort);
    }

    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody Comment comment,
                              @RequestParam Long userId,
                              @RequestParam Long postId) throws PostNotFoundException {
        return commentService.addComment(comment, userId, postId);
    }

    @PutMapping("/update-comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId,
                                 @RequestBody Comment comment,
                                 @RequestParam Long userId,
                                 @RequestParam Long postId) throws PostNotFoundException {
        return commentService.updateComment(comment, commentId, postId, userId);
    }


    @DeleteMapping("/delete-comment")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
    }

}
