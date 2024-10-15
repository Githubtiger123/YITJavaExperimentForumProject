package com.YITJavaExperimentProjects;

import com.YITJavaExperimentProjects.model.User;
import com.YITJavaExperimentProjects.service.UserService;
import com.YITJavaExperimentProjects.view.ForumSystemRegisterAndSignPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ForumSystemRegisterAndSignPage registerAndSignPage = new ForumSystemRegisterAndSignPage();
        registerAndSignPage.MainPage();
    }

    @Test
    public void test() {

        UserService userService = new UserService();
//        userService.registerUser(new User("i坤坤","A1Dik0@kk.com","123456"));

        userService.loginUser(new User("i坤坤","A1Dik0@kk.com","123456"));
    }

}