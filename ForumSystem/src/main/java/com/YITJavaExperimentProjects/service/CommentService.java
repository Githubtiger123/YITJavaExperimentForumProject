package com.YITJavaExperimentProjects.service;

import com.YITJavaExperimentProjects.dao.CommentMapper;
import com.YITJavaExperimentProjects.dao.PostMapper;
import com.YITJavaExperimentProjects.model.Comment;
import com.YITJavaExperimentProjects.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentService {

//    private CommentMapper commentMapper;
//
//    {
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            commentMapper = sqlSession.getMapper(CommentMapper.class);
//        }
//    }

    //- `createComment()`: 处理创建评论请求，并在控制台输出结果。
    public boolean insertComment(Comment comment) {

        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            commentMapper.insertComment(comment);
        }
        return true;
    }

    //- `viewComments()`: 处理查看评论请求，并在控制台输出评论信息。
    public List<Comment> viewComments(int postId) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            return commentMapper.getCommentByPostId(postId);
        }
    }
}
