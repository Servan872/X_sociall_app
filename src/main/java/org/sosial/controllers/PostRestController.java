package org.sosial.controllers;

import lombok.RequiredArgsConstructor;
import org.sosial.dto.request.PostRequest;
import org.sosial.dto.response.PostResponse;
import org.sosial.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRestController {
    private final PostService postService;


    @GetMapping("/all")
    public List<PostResponse> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{username}/all")
    public List<PostResponse> getAllPostByUser(@PathVariable String username) {
        return postService.getAllPostByUser(username);
    }

    @PostMapping("{username}/create")
    public PostResponse createPostByUser(@PathVariable String username, @RequestBody PostRequest postRequest) {
        return postService.createPostByUser(username, postRequest);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(postId, postRequest));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        if (postService.deletePostById(postId)) {
            return ResponseEntity.ok("Deleted post");
        } else {
            return ResponseEntity.badRequest().body("Post not found");
        }
    }


}
