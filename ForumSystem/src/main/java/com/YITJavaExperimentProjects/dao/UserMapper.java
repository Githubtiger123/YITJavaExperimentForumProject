package com.YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //- `insertUser()`: 插入用户。
    @Insert("insert into Users(userName, email, passwd) values (#{userName}, #{email}, #{passwd})")
    int insertUser(User user);

    //- `selectUserById()`: 根据用户ID查询用户。
    @Select("select * from Users where userId = #{uid}")
    User selectUserById(int uid);


    //- `selectUserByUsername()`: 根据用户名查询用户。
    @Select("select * from Users where userName = #{username}")
    User selectUserByUsername(String username);

    //- `selectUserByUsername()`: 根据邮箱查询用户。
    @Select("select * from Users where email = #{email}")
    User selectUserByEmail(String email);


    //删除用户
    int deleteUserById(int uid);

    //查询用户发的帖子
    @Select("select * from Post where userId = #{uid}")
    List<Post> getPostByUserId(int uid);

}
