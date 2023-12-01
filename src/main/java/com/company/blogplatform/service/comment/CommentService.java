package com.company.blogplatform.service.comment;


import com.company.blogplatform.exception.CommentNotFoundException;
import com.company.blogplatform.exception.PostNotFoundException;
import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.comments.Comment;
import com.company.blogplatform.model.posts.Post;
import com.company.blogplatform.model.users.User;
import com.company.blogplatform.repository.comments.CommentRepository;
import com.company.blogplatform.repository.posts.PostRepository;
import com.company.blogplatform.repository.users.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Page<Comment> getAllComments(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return commentRepository.findAll(pageable);
    }

    public Comment getCommentById(Long commentId) {
        if (commentId == null || commentId <= 0) {
            throw new IllegalArgumentException("Invalid comment id");
        } else {
            return commentRepository.findById(commentId)
                    .orElseThrow(() -> new CommentNotFoundException("Comment by id " + commentId + " was not found"));
        }
    }

    public Page<Comment> getCommentsByPostId(Long postId, Integer pageNumber, Integer pageSize, String sort) {
        if (postId == null || postId <= 0) {
            throw new IllegalArgumentException("Invalid post id");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Invalid page number");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page size");
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort));
        return commentRepository.findByPostId(postId, pageable);
    }


    public Comment addComment(Comment comment, Long postId, Long userId) throws PostNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post by id " + postId + " was not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));


        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }


    public Comment updateComment(Comment comment, Long commentId, Long postId, Long userId) throws PostNotFoundException {
        if (commentId == null || commentId <= 0) {
            throw new IllegalArgumentException("Invalid comment id");
        }
        if (comment == null) {
            throw new IllegalArgumentException("Invalid comment data");
        }

        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment by id " + commentId + " was not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post by id " + postId + " was not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));

        existingComment.setDateCommented(comment.getDateCommented());
        existingComment.setText(comment.getText());
        existingComment.setPost(post);
        existingComment.setUser(user);
        return commentRepository.save(existingComment);
    }


    public void deleteComment(Long commentId) {
        if (commentId == null || commentId <= 0) {
            throw new IllegalArgumentException("Invalid comment id");
        } else {
            commentRepository.deleteById(commentId);
        }
    }


}
