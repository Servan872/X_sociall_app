package org.sosial.dto.request;

import org.sosial.entities.Post;

import java.time.LocalDateTime;

public record PostRequest(String postText) {

    public static Post convertePostRequestToPost(PostRequest postRequest) {
        Post post = new Post();
        post.setPostText(postRequest.postText);
        post.setCreatedPost(LocalDateTime.now());
        return post;
    }






}
