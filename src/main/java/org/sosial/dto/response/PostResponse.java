package org.sosial.dto.response;

import org.sosial.entities.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(String username,
                           Long postId,
                           String postText,
                           LocalDateTime createdPost) {


    public static PostResponse convertPostToPostResponse(Post posts) {
       return new PostResponse(posts.getUser().getUsername(), posts.getPostId(), posts.getPostText(), posts.getCreatedPost());
    }
}
