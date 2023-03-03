package com.nirmalya.fbclone.service;

import com.nirmalya.fbclone.entity.PostEntity;
import com.nirmalya.fbclone.model.Post;
import com.nirmalya.fbclone.repository.PostEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostEntityRepository postEntityRepository;

    public PostServiceImpl(PostEntityRepository postEntityRepository) {
        this.postEntityRepository = postEntityRepository;
    }

    @Override
    public Post addPost(Post post) throws Exception {
        try {
            PostEntity postEntity = new PostEntity();
            BeanUtils.copyProperties(post, postEntity);

//            if (post.getFile() != null && !post.getFile().equalsIgnoreCase("null"))
//                postEntity.setImage(post.getFile());
//            else
//                postEntity.setImage(null);

            postEntity = postEntityRepository.save(postEntity);
            post.setId(postEntity.getId());
            post.setFile(null);
            post.setImage(postEntity.getImage());

        } catch (Exception exception) {
            throw new Exception("Could Not Save Post." + exception);
        }
        return post;
    }

    @Override
    public List<Post> getPost() {
        List<PostEntity> postEntities = postEntityRepository.findAll(
                Sort.by(Sort.Direction.DESC, "timeStamp")
        );
        List<Post> posts = new ArrayList<>();
        posts = postEntities.stream()
                .map(postEntity ->
                        Post.builder()
                                .id(postEntity.getId())
                                .name(postEntity.getName())
                                .post(postEntity.getPost())
                                .timeStamp(postEntity.getTimeStamp())
                                .email(postEntity.getEmail())
                                .profilePic(postEntity.getProfilePic())
                                .image(postEntity.getImage())
                                .build())
                .collect(Collectors.toList());
        return posts;
    }
}
