package com.YITJavaExperimentProjects.view;

import com.YITJavaExperimentProjects.controller.UserController;
import com.YITJavaExperimentProjects.model.User;

import java.util.Scanner;

public class ForumSystemRegisterAndSignPage {

    private UserController userController = new UserController();

    private void displayLoginPage() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("------------登录页面----------------");
            System.out.print("请输入用户名: ");
            String userName = scanner.nextLine();

            System.out.print("请输入密码: ");
            String passwd = scanner.nextLine();

            // 这里可以添加登录验证逻辑
            // 例如：检查用户名和密码是否正确
            // 如果验证成功，设置 loggedIn 为 true
            if (validateLogin(userName, passwd)) {
                loggedIn = true;
                System.out.println("-------------------------------------");
                System.out.println("登录成功！");
                ForumSystemIndexPage forumSystemIndexPage = new ForumSystemIndexPage();
                forumSystemIndexPage.IndexPage();
            } else {
                System.out.println("用户名或密码错误，请重新输入。");
            }
        }

        scanner.close();
    }

    private void displayRegisterPage() {
        Scanner scanner = new Scanner(System.in);
        boolean registered = false;

        while (!registered) {
            System.out.println("------------注册页面----------------");
            System.out.print("请输入用户名: ");
            String userName = scanner.nextLine();

            System.out.print("请输入邮箱: ");
            String email = scanner.nextLine();

            System.out.print("请输入密码: ");
            String passwd = scanner.nextLine();

            // 这里可以添加注册验证逻辑
            // 例如：检查用户名和邮箱是否已存在
            // 如果验证成功，设置 registered 为 true
            if (validateRegistration(userName, email, passwd)) {
                registered = true;
                System.out.println("-------------------------------------");
                System.out.println("注册成功！");
            } else {
                System.out.println("用户名或邮箱已存在，请重新输入。");
            }
        }

        scanner.close();
    }

    public void MainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainPage();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    displayRegisterPage();
                case 2:
                    displayLoginPage();
                case 3:
                    System.out.println("退出系统...");
                    running = false;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainPage() {
        System.out.println("------------论坛系统----------------");
        System.out.println("(1) 注册");
        System.out.println("(2) 登录");
        System.out.println("(3) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    // 模拟登录验证逻辑
    private boolean validateLogin(String userName, String passwd) {
        return userController.login(userName, passwd);
    }

    // 模拟注册验证逻辑
    private boolean validateRegistration(String userName, String email, String passwd) {
        // 这里可以添加实际的注册验证逻辑
        // 例如：检查用户名和邮箱是否已存在
        return userController.register(userName, email, passwd);
    }
}
