package com.YITJavaExperimentProjects.view;

import java.util.Scanner;

public class ForumSystemPersonalPage {


    private static final String[] POSTS = {"个人帖子 1: 这是一个示例帖子标题", "个人帖子 2: 这是一个示例帖子标题", "个人帖子 3: 这是一个示例帖子标题", "个人帖子 4: 这是一个示例帖子标题", "个人帖子 5: 这是一个示例帖子标题"};

    public void PersonalMainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayPersonalPage();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    viewPost(scanner);
                    break;
                case 2:
                    System.out.println("退出个人主页...");
                    running = false;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayPersonalPage() {
        System.out.println("------------个人主页----------------");
        for (int i = 0; i < POSTS.length; i++) {
            System.out.println((i + 1) + ". " + POSTS[i]);
        }
        System.out.println("(1) 查看帖子");
        System.out.println("(2) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    private static void viewPost(Scanner scanner) {
        System.out.print("请输入要查看的帖子序号: ");
        int postNumber = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        if (postNumber >= 1 && postNumber <= POSTS.length) {
            String selectedPost = POSTS[postNumber - 1];
            System.out.println("查看帖子: " + selectedPost);
            // 这里可以添加实际的查看帖子逻辑
            // 例如：显示帖子的详细内容
        } else {
            System.out.println("无效的帖子序号，请重新输入。");
        }
    }
}
