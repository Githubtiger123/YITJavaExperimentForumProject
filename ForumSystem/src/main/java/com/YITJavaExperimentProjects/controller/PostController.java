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
}
