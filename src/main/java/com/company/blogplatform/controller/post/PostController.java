package com.company.blogplatform.controller.post;

import com.company.blogplatform.exception.CategoryNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.service.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Posts", description = "The Posts API")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Like Post", description = "Likes a post by its ID")
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long postId) {
        try {
            postService.likePost(postId);
            return ResponseEntity.ok().body(postService.getPostById(postId));
        } catch (PostNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Dislike Post", description = "Dislikes a post by its ID")
    @PostMapping("/posts/{postId}/dislike")
    public ResponseEntity<Post> dislikePost(@PathVariable Long postId) {
        try {
            postService.dislikePost(postId);
            return ResponseEntity.ok().body(postService.getPostById(postId));
        } catch (PostNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get All Posts", description = "Retrieves all posts with pagination")
    @GetMapping("/posts/all-posts")
    public Page<Post> getAllPosts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort) {
        return postService.getAllPosts(pageNumber, pageSize, sort);
    }

    @Operation(summary = "Get Post By ID", description = "Retrieves a post by its ID")
    @GetMapping("/posts/post/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        try {
            Post post = postService.getPostById(postId);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Posts By User ID", description = "Retrieves all posts made by a specific user")
    @GetMapping("/posts/posts-by-user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        try {
            List<Post> posts = postService.getPostsByUserId(userId);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create Post", description = "Creates a new post")
    @PostMapping("/posts/create-post/{userId}/{categoryId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId, @PathVariable Long categoryId) {
        Post createdPost = postService.addPost(post, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Post", description = "Updates an existing post")
    @PutMapping("/posts/update-post/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long postId, @RequestParam Long userId, @RequestParam Long categoryId) {
        try {
            Post updatedPost = postService.updatePost(postId, post, userId, categoryId);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (PostNotFoundException | UserNotFoundException | CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete Post", description = "Deletes a post by its ID")
    @DeleteMapping("/posts/delete-post/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
