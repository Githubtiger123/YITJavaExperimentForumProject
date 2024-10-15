package com.YITJavaExperimentProjects.service;

import com.YITJavaExperimentProjects.dao.CommentMapper;
import com.YITJavaExperimentProjects.dao.TopicMapper;
import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.model.Topic;
import com.YITJavaExperimentProjects.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TopicService {

//    private TopicMapper topicMapper;
//
//    {
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            topicMapper = sqlSession.getMapper(TopicMapper.class);
//        }
//    }

    //- `createTopic()`: 创建话题。
    public boolean createTopic(Topic topic) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);
            topicMapper.insertTopic(topic);
        }
        return true;
    }

    //- `getPostByTopicId()`: 根据话题ID获取话题的帖子。
    public List<Post> getPostByTopicId(int topicId) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);
            return topicMapper.selectPostByTopicId(topicId);
        }
    }

    //- `getTopics()`: 获取所有话题。

    public List<Topic> getTopics() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);
            return topicMapper.selectTopics();
        }
    }
}
