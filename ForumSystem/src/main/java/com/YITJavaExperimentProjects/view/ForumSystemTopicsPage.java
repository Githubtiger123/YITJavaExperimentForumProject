package com.YITJavaExperimentProjects.view;

import com.YITJavaExperimentProjects.controller.PostController;
import com.YITJavaExperimentProjects.model.Post;

import java.util.List;
import java.util.Scanner;

public class ForumSystemTopicsPage {

    private static final String[] TOPICS = {"Java 编程", "Python 编程", "C++ 编程", "Web 开发", "数据库", "操作系统", "网络编程", "数据结构与算法", "人工智能", "机器学习"};
    private PostController postController = new PostController();

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

    }

    private void displayTopicsPage() {
        System.out.println("------------话题页面----------------");
        for (int i = 0; i < TOPICS.length; i++) {
            System.out.println((i + 1) + ". " + TOPICS[i]);
        }
        System.out.println("(1) 选择话题");
        System.out.println("(2) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    private void selectTopic(Scanner scanner) {
        System.out.print("请输入要选择的话题序号: ");
        int topicNumber = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        if (topicNumber >= 1 && topicNumber <= TOPICS.length) {
            String selectedTopic = TOPICS[topicNumber - 1];
            System.out.println("你选择了话题: " + selectedTopic);
            // 这里可以添加实际的查看话题逻辑
            // 例如：显示该话题下的帖子列表
            PostMainPage();
        } else {
            System.out.println("无效的话题序号，请重新输入。");
        }
    }

    public void PostMainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainPage();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewPost();
                    break;
                case 2:
//                    previousPage();
                    break;
                case 3:
//                    nextPage();
                    break;
                case 4:
                    System.out.println("退出系统...");
                    running = false;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
                    break;
            }
        }

//        scanner.close();
    }

    public void displayMainPage() {
        System.out.println("------------论坛系统----------------");
//        System.out.println("当前页码: " + currentPage);
        displayPosts();
        System.out.println("(1) 查看帖子");
//        System.out.println("(2) 上翻页");
//        System.out.println("(3) 下翻页");
        System.out.println("(4) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    private void displayPosts() {
        List<Post> allPosts = postController.getAllPosts();
        int i = 1;
        for (Post allPost : allPosts) {
//            System.out.println(POSTS_PER_PAGE * (currentPage - 1) + i + allPost.getTitle());
            System.out.println(i + allPost.getTitle());
            i++;
        }
    }


    private void viewPost() {
        boolean viewing = true;

        while (viewing) {
            System.out.print("请输入要查看的帖子序号 (输入 -1 退出,-2评论): ");
            Scanner scanner = new Scanner(System.in);
            int postNumber = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            System.out.println("111111111111111111");
            if (postNumber == -1) {
                viewing = false;
                System.out.println("退出查看帖子...");
            } else if (postNumber == -2) {
                System.out.println("请输入评论内容回车");
                printPost(-1);
            } else {
                printPost(-1);
            }
        }
    }

    private void printPost(int i) {

        System.out.print("题目---");
        System.out.println();
    }
}
