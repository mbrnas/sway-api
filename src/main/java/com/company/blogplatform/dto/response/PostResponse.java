package com.company.blogplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate datePosted;
    private int likes;
    private int dislikes;
    private UserResponse user;
    private String categoryName;
}
