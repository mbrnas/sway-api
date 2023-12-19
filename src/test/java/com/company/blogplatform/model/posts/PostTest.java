package com.company.blogplatform.model.posts;

import com.company.blogplatform.model.categories.Category;
import com.company.blogplatform.model.comments.Comment;
import com.company.blogplatform.model.users.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void testGettersAndSetters() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
        post.setDatePosted(LocalDate.now());
        post.setLikes(0);
        post.setDislikes(0);
        post.setUser(new User());
        post.setCategory(new Category());
        post.setComments(new HashSet<>());

        assertEquals(1L, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Content", post.getContent());
        assertEquals(LocalDate.now(), post.getDatePosted());
        assertEquals(0, post.getLikes());
        assertEquals(0, post.getDislikes());
        assertEquals(new User(), post.getUser());
        assertEquals(new Category(), post.getCategory());
        assertEquals(new HashSet<>(), post.getComments());
    }

    @Test
    void testToString() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");

        assertNotNull(post.toString());
        assertTrue(post.toString().contains("Test Title"));
    }

    @Test
    void getId_ShouldReturnCorrectId() {
        Post post = new Post();
        post.setId(1L);
        assertEquals(1L, post.getId());
    }

    @Test
    void getTitle_ShouldReturnCorrectTitle() {
        Post post = new Post();
        post.setTitle("Test Title");
        assertEquals("Test Title", post.getTitle());
    }

    @Test
    void getContent_ShouldReturnCorrectContent() {
        Post post = new Post();
        post.setContent("Test Content");
        assertEquals("Test Content", post.getContent());
    }

    @Test
    void getDatePosted_ShouldReturnCorrectDatePosted() {
        Post post = new Post();
        post.setDatePosted(LocalDate.now());
        assertEquals(LocalDate.now(), post.getDatePosted());
    }

    @Test
    void getLikes_ShouldReturnCorrectNumberOfLikes() {
        Post post = new Post();
        post.setLikes(20);
        assertEquals(20, post.getLikes());
    }

    @Test
    void getDislikes_ShouldReturnCorrectNumberOfDislikes() {
        Post post = new Post();
        post.setDislikes(25);
        assertEquals(25, post.getDislikes());
    }

    @Test
    void getUser_ShouldReturnValidUser() {
        Post post = new Post();
        post.setUser(new User());
        assertEquals(new User(), post.getUser());
    }

    @Test
    void getCategory_ShouldReturnCorrectCategory() {
        Post post = new Post();
        post.setCategory(new Category());
        assertEquals(new Category(), post.getCategory());
    }

    @Test
    void getComments_ShouldReturnValidCommentsForPost() {
        Post post = new Post();
        Set<Comment> comments = new HashSet<>();
        post.setComments(comments);
        assertEquals(comments, post.getComments());
    }


    @Test
    void setId_shouldSetCorrectId() {
        Post post = new Post();
        post.setId(1L);
        assertEquals(1L, post.getId());
    }

    @Test
    void setTitle() {
        Post post = new Post();
        post.setTitle("Test Title");
        assertEquals("Test Title", post.getTitle());
    }

    @Test
    void setContent() {
        Post post = new Post();
        post.setContent("Test Content");
        assertEquals("Test Content", post.getContent());
    }

    @Test
    void setDatePosted() {
        Post post = new Post();
        post.setDatePosted(LocalDate.now());
        assertEquals(LocalDate.now(), post.getDatePosted());
    }

    @Test
    void setLikes() {
        Post post = new Post();
        post.setLikes(30);
        assertEquals(30, post.getLikes());
    }

    @Test
    void setDislikes() {
        Post post = new Post();
        post.setDislikes(35);
        assertEquals(35, post.getDislikes());
    }

    @Test
    void setUser() {
        Post post = new Post();
        post.setUser(new User());
        assertEquals(new User(), post.getUser());
    }

    @Test
    void setCategory() {
        Post post = new Post();
        post.setCategory(new Category());
        assertEquals(new Category(), post.getCategory());
    }

    @Test
    void setComments() {
        Post post = new Post();
        Set<Comment> comments = new HashSet<>();
        post.setComments(comments);
        assertEquals(comments, post.getComments());
    }
}