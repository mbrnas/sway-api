package com.company.blogplatform.service.posts;

import com.company.blogplatform.exception.CategoryNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.categories.Category;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.model.users.User;
import com.company.blogplatform.repository.categories.CategoryRepository;
import com.company.blogplatform.repository.posts.PostRepository;
import com.company.blogplatform.repository.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Post addPost(Post post, Long userId, Long categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id " + categoryId + " was not found"));

        post.setUser(user);
        post.setCategory(category);

        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post postDetails, Long userId, Long categoryId) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post by id " + postId + " was not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id " + categoryId + " was not found"));

        existingPost.setTitle(postDetails.getTitle());
        existingPost.setContent(postDetails.getContent());
        existingPost.setUser(user);
        existingPost.setCategory(category);

        return postRepository.save(existingPost);
    }

}
