package com.company.blogplatform.controller.posts;

import com.company.blogplatform.exception.CategoryNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.service.posts.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post/{userId}/{categoryId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId, @PathVariable Long categoryId) {
        Post createdPost = postService.addPost(post, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/update-post/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long postId, @RequestParam Long userId, @RequestParam Long categoryId) {
        try {
            Post updatedPost = postService.updatePost(postId, post, userId, categoryId);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (PostNotFoundException | UserNotFoundException | CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
