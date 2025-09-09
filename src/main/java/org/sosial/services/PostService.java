package org.sosial.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sosial.dto.request.PostRequest;
import org.sosial.dto.response.PostResponse;
import org.sosial.entities.Post;
import org.sosial.entities.User;
import org.sosial.repositories.PostRepository;
import org.sosial.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "crateDate"));
        List<PostResponse> collect = posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
        return collect;

    }

    @Transactional
    public List<PostResponse> getAllPostByUser(String username) {
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new IllegalArgumentException("Username not found"));
        List<Post> posts = postRepository.findPostByUser(user);
        return posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
    }

    @Transactional
    public PostResponse createPostByUser(String username, PostRequest postRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));
        Post post = PostRequest.convertePostRequestToPost(postRequest);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return PostResponse.convertPostToPostResponse(savedPost);
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("Post Id not found"));
        Optional.ofNullable(postRequest.postText()).ifPresent(foundPost::setPostText);
        Post savedPost = postRepository.save(foundPost);
        return PostResponse.convertPostToPostResponse(savedPost);
    }


    public Boolean deletePostById(Long postId) {
        try {
            Post foundPost = postRepository.findById(postId).orElseThrow(() ->
                    new IllegalArgumentException("Post Id not found"));
            postRepository.delete(foundPost);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
