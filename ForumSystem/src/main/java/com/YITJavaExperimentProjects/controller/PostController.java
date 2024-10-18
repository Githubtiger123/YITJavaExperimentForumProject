package com.YITJavaExperimentProjects.controller;

import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.service.PostService;

import java.util.List;

public class PostController {

    PostService postService = new PostService();

    //获取所有的帖子
    public List<Post> getAllPosts() {

        return postService.getAllPosts();
    }

    //根据帖子ID获取帖子信息。
    public Post getPostById(int id) {
        return postService.getPostById(id);
    }
}
