package com.YITJavaExperimentProjects.service;

import com.YITJavaExperimentProjects.dao.UserMapper;
import com.YITJavaExperimentProjects.model.User;
import com.YITJavaExperimentProjects.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserService {

//    private UserMapper userMapper;
//
//    {
//        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
//            userMapper = sqlSession.getMapper(UserMapper.class);
//        }
//    }

    //- `registerUser()`: 注册用户。
    public boolean registerUser(User user) {

        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //判断用户名，电子邮件是否被占用
            User user1 = userMapper.selectUserByEmail(user.getEmail());
            User user2 = userMapper.selectUserByUsername(user.getUserName());
            //插入用户
            if (user1 != null) {
                System.out.println("error:邮箱被占用");
                return false;
            } else if (user2 != null) {
                System.out.println("error:用户名被占用");
                return false;
            } else {
                userMapper.insertUser(user);
            }
            return true;
        }
    }

    //- `loginUser()`: 用户登录。
    public boolean loginUser(User user) {

        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //判断是否注册
            User user1 = userMapper.selectUserByUsername(user.getUserName());
            if (user1 == null) {
                System.out.println("用户未注册");
                return false;
            }

            //判断用户名和密码是否正确
            if (!(user1.getPasswd().equals(user.getPasswd()))) {
                System.out.println("用户密码错误");
            }
        }
        return true;
    }

    //- `getUserById()`: 根据用户ID获取用户信息。
    public User getUserById(String name) {

        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //一定时登陆成功后的不用校验直接查询
            return userMapper.selectUserByUsername(name);
        }
    }
}
