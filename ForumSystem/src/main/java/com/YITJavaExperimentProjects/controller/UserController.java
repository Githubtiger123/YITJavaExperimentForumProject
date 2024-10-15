package com.YITJavaExperimentProjects.controller;

import com.YITJavaExperimentProjects.model.User;
import com.YITJavaExperimentProjects.service.UserService;

public class UserController {

    private UserService userService;

    //- `register()`: 处理用户注册请求，并在控制台输出结果。
    public boolean register(String username, String email, String password) {

        userService = new UserService();
        boolean b = userService.registerUser(new User(username, email, password));
        return b;
    }

    //- `login()`: 处理用户登录请求，并在控制台输出结果。
    public boolean login(String username, String password) {

        userService = new UserService();
        boolean b = userService.loginUser(new User(username, null, password));
        return b;
    }

}
