package com.YITJavaExperimentProjects.service;

import com.YITJavaExperimentProjects.dao.PostMapper;
import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PostService {

//    private PostMapper postMapper;
//
//    {
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            postMapper = sqlSession.getMapper(PostMapper.class);
//        }
//    }

    //- `createPost()`: 创建帖子。
    public boolean createPost(Post post) {

        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            postMapper.insertPost(post);
        }
        return true;
    }

    //- `getPostById()`: 根据帖子ID获取帖子信息。
    public Post getPostById(int id) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            return postMapper.selectPostById(id);
        }
    }

    //- `getPostsByUserId()`: 根据用户ID获取用户的所有帖子。
    public List<Post> getPostsByUserId(int userId) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            return postMapper.selectPostsByUserId(userId);
        }
    }

    public List<Post> getAllPosts() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            return postMapper.getAllPosts();
        }
    }

}
