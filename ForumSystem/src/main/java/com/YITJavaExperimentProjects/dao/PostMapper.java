package com.YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {

    //- `insertPost()`: 创建帖子。
    @Insert("insert into Post(title,content,userId,topicId) values (#{title}," +
            "#{content},#{userId},#{topicId})")
    int insertPost(Post post);

    //- `selectPostById()`: 根据帖子ID查询帖子。
    @Select("select * from Post where PostId = #{postId}")
    Post selectPostById(int postId);

    //- `selectPostsByUserId()`: 根据用户ID查询用户的所有帖子。
    @Select("select * from Post where userId = #{userId}")
    List<Post> selectPostsByUserId(int userId);
}
