package com.YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {


    //- `insertComment()`: 插入评论。
    @Insert("insert into Comments(content,userId,postId) values(#{content}" +
            ",#{userId},#{postId})")
    int insertComment(Comment comment);

    //- `selectCommentsByPostId()`: 根据帖子ID查询所有评论。
    @Select("select * from Comments where postId=#{postId}")
    List<Comment> getCommentByPostId(int postId);

    //- `selectCommentsByUserId()`: 根据用户ID查询用户的所有评论。
    @Select("select * from Comments where userId=#{uid}")
    List<Comment> selectCommentsByUserId(int uid);

}
