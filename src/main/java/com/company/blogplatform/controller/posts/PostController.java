package com.company.blogplatform.controller.posts;

import com.company.blogplatform.exception.CategoryNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.service.posts.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        try {
            Post post = postService.getPostById(postId);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts-by-user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        try {
            List<Post> posts = postService.getPostsByUserId(userId);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
