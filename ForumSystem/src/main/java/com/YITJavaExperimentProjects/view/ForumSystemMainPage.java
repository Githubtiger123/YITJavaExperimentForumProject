package com.YITJavaExperimentProjects.view;

import com.YITJavaExperimentProjects.controller.PostController;
import com.YITJavaExperimentProjects.model.Post;

import java.util.List;
import java.util.Scanner;

public class ForumSystemMainPage {

    private int currentPage = 1; // 当前页码
    private final int POSTS_PER_PAGE = 20; // 每页显示的帖子数量
    private PostController postController = new PostController();
    List<Post> allPosts;

    public void PostMainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainPage();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    viewPost(scanner.nextInt());
                    break;
                case 2:
                    previousPage();
                    break;
                case 3:
                    nextPage();
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

        scanner.close();
    }

    private void displayMainPage() {
        System.out.println("------------论坛系统----------------");
        System.out.println("当前页码: " + currentPage);
        displayPosts();
        System.out.println("(1) 查看帖子");
        System.out.println("(2) 上翻页");
        System.out.println("(3) 下翻页");
        System.out.println("(4) 退出");
        System.out.println("-------------------------------------");
        System.out.print("请输入你的选择: ");
    }

    private void displayPosts() {
        List<Post> allPosts = postController.getAllPosts();
        int i = 1;
        for (Post allPost : allPosts) {
            System.out.println(POSTS_PER_PAGE * (currentPage - 1) + i + allPost.getContent());
        }
    }


    private void viewPost(int index) {
        boolean viewing = true;

        while (viewing) {
            System.out.print("请输入要查看的帖子序号 (输入 1 退出): ");
            Scanner scanner = new Scanner(System.in);
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

    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
        } else {
            System.out.println("已经是第一页了。");
        }
    }

    private void nextPage() {
        // 这里可以添加实际的翻页逻辑
        // 例如：检查是否有下一页
        currentPage++;
        System.out.println("已经是最后一页了。");
    }
}
