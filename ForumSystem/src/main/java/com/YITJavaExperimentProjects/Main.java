package com.YITJavaExperimentProjects;

import com.YITJavaExperimentProjects.dao.UserMapper;
import com.YITJavaExperimentProjects.model.User;
import com.YITJavaExperimentProjects.service.UserService;
import com.YITJavaExperimentProjects.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserByUsername("admin");
            System.out.println(user);

        }

    }

    @Test
    public void test() {

        UserService userService = new UserService();
//        userService.registerUser(new User("i坤坤","A1Dik0@kk.com","123456"));

        userService.loginUser(new User("i坤坤","A1Dik0@kk.com","123456"));
    }

}