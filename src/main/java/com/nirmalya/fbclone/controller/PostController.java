package com.nirmalya.fbclone.controller;

import com.nirmalya.fbclone.model.Post;
import com.nirmalya.fbclone.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post addPost(@RequestParam Map<String, String> requestParams) throws Exception {
        String profilePic = requestParams.get("profilePic");
        String email = requestParams.get("email");
        String post = requestParams.get("post");
        String name = requestParams.get("name");
        String file = requestParams.get("file");

        Post postTemp = Post.builder()
                .profilePic(profilePic)
                .email(email)
                .post(post)
                .name(name)
                .file(file)
                .timeStamp(new Date().toString())
                .build();

        postTemp = postService.addPost(postTemp);
        return postTemp;
    }

    @GetMapping
    public List<Post> getPost() {
        return postService.getPost();
    }
}
