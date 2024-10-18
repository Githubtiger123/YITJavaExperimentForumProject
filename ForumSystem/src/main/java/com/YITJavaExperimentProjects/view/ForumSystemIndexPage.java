package com.YITJavaExperimentProjects.view;

import java.util.Scanner;

public class ForumSystemIndexPage {

    public void IndexPage() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainPage();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("进入主页面...");
                    new ForumSystemMainPage().PostMainPage();
                    break;
                case 2:
                    System.out.println("进入话题分类...");
                    new ForumSystemTopicsPage().TopicsMainPage();
                    break;
                case 3:
                    System.out.println("进入个人主页...");
                    new ForumSystemPersonalPage().PersonalMainPage();
                    break;
                case 4:
                    System.out.println("进入个人设置...");
                    // 在这里添加个人设置的逻辑
                    break;
                case 5:
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
        System.out.println("(1) 主页面");
        System.out.println("(2) 话题分类");
        System.out.println("(3) 个人主页");
        System.out.println("(4) 个人设置");
        System.out.println("(5) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }
}
