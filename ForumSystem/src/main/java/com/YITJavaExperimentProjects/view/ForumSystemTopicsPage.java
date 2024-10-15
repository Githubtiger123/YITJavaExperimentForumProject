package com.YITJavaExperimentProjects.view;

import java.util.Scanner;

public class ForumSystemTopicsPage {

    private static final String[] TOPICS = {"Java 编程", "Python 编程", "C++ 编程", "Web 开发", "数据库", "操作系统", "网络编程", "数据结构与算法", "人工智能", "机器学习"};

    public void TopicsMainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayTopicsPage();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    selectTopic(scanner);
                    break;
                case 2:
                    System.out.println("退出话题页面...");
                    running = false;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayTopicsPage() {
        System.out.println("------------话题页面----------------");
        for (int i = 0; i < TOPICS.length; i++) {
            System.out.println((i + 1) + ". " + TOPICS[i]);
        }
        System.out.println("(1) 选择话题");
        System.out.println("(2) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    private static void selectTopic(Scanner scanner) {
        System.out.print("请输入要选择的话题序号: ");
        int topicNumber = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        if (topicNumber >= 1 && topicNumber <= TOPICS.length) {
            String selectedTopic = TOPICS[topicNumber - 1];
            System.out.println("你选择了话题: " + selectedTopic);
            // 这里可以添加实际的查看话题逻辑
            // 例如：显示该话题下的帖子列表
        } else {
            System.out.println("无效的话题序号，请重新输入。");
        }
    }

    private static void displayPosts() {
        // 这里可以添加实际的帖子数据
        // 例如：从数据库或文件中读取帖子数据

    }

    private static void viewPost(Scanner scanner) {
        boolean viewing = true;

        while (viewing) {
            System.out.print("请输入要查看的帖子序号 (输入 1 退出): ");
            int postNumber = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            if (postNumber == 1) {
                viewing = false;
                System.out.println("退出查看帖子...");
            } else {
                // 这里可以添加实际的查看帖子逻辑
                // 例如：根据帖子序号显示帖子内容
                System.out.println("查看帖子 " + postNumber + "...");
            }
        }
    }
}
