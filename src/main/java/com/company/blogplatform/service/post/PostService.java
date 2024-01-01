package com.company.blogplatform.service.post;

import com.company.blogplatform.dto.response.PostResponse;
import com.company.blogplatform.dto.response.UserResponse;
import com.company.blogplatform.exception.CategoryNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.categories.Category;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.model.users.User;
import com.company.blogplatform.repository.categories.CategoryRepository;
import com.company.blogplatform.repository.posts.PostRepository;
import com.company.blogplatform.repository.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public void likePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public void dislikePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
    }

    public List<PostResponse> getAllPosts(String sort) {
        List<Post> posts;
        if (sort != null && !sort.trim().isEmpty()) {
            posts = postRepository.findAll(Sort.by(Sort.Direction.ASC, sort));
        } else {
            posts = postRepository.findAll();
        }
        return posts.stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }


    public PostResponse getPostById(Long id) throws PostNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post by id " + id + " was not found"));

        PostResponse postResponse = modelMapper.map(post, PostResponse.class);
        UserResponse userResponse = modelMapper.map(post.getUser(), UserResponse.class);
        postResponse.setUser(userResponse);
        postResponse.setCategoryName(post.getCategory().getName());
        return postResponse;
    }


    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.isEmpty()) {
            throw new UserNotFoundException("User and his/hers posts by id " + userId + " were not found");
        } else {
            return posts;
        }
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

    public void deletePost(Long postId) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post by id " + postId + " was not found"));
        if (postRepository.findById(postId).isEmpty()) {
            throw new PostNotFoundException("Post by id " + postId + " was not found");
        } else {
            postRepository.delete(existingPost);
        }
    }

}
