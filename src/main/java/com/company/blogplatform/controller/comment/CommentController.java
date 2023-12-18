package com.company.blogplatform.controller.comment;

import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.model.comments.Comment;
import com.company.blogplatform.service.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Comments", description = "The Comments API")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get All Comments", description = "Retrieves all comments with pagination")
    @GetMapping("/all-comments")
    public Page<Comment> getAllComments(@RequestParam(defaultValue = "0") Integer pageNumber,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sort) {
        return commentService.getAllComments(pageNumber, pageSize, sort);
    }

    @Operation(summary = "Get Comment By ID", description = "Retrieves a comment by its ID")
    @GetMapping("/comment/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @Operation(summary = "Get Comments By Post ID", description = "Retrieves comments for a specific post with pagination")
    @GetMapping("/{postId}")
    public Page<Comment> getCommentsByPostId(@PathVariable Long postId,
                                             @RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sort) {
        return commentService.getCommentsByPostId(postId, pageNumber, pageSize, sort);
    }

    @Operation(summary = "Add Comment", description = "Adds a new comment to a post")
    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody Comment comment,
                              @RequestParam Long userId,
                              @RequestParam Long postId) throws PostNotFoundException {
        return commentService.addComment(comment, userId, postId);
    }

    @Operation(summary = "Update Comment", description = "Updates an existing comment")
    @PutMapping("/update-comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId,
                                 @RequestBody Comment comment,
                                 @RequestParam Long userId,
                                 @RequestParam Long postId) throws PostNotFoundException {
        return commentService.updateComment(commentId, comment, userId, postId);
    }

    @Operation(summary = "Delete Comment", description = "Deletes a comment by its ID")
    @DeleteMapping("/delete-comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

}
