package com.nirmalya.fbclone.service;

import com.nirmalya.fbclone.model.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post postTemp) throws Exception;

    List<Post> getPost();
}
