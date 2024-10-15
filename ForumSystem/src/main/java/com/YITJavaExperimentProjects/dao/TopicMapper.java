package com.YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TopicMapper {


    //- `insertTopic()`: 插入话题。
    @Insert("insert into Topic(topicName,description) values (#{topicName},#{description})")
    int insertTopic(Topic topic);


    //- `selectPostByTopicId()`: 根据话题ID查询帖子。
    @Select("select * from Post where topicId = #{topicId}")
    List<Post> selectPostByTopicId(int topicId);

    //- `selectTopics()`: 查询所有话题。
    @Select("select * from Topic")
    List<Topic> selectTopics();
}
