package YITJavaExperimentProjects.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// 论坛应用程序
public class ForumApp {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Post> posts = new ArrayList<>();
    static ArrayList<Topic> topics = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static User loggedInUser = null;

    public static void main(String[] args) {
        loadData();
        boolean running = true;

        while (running) {
            System.out.println("\n=== 欢迎来到简易论坛 ===");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
            System.out.print("请选择操作（输入数字）：");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    running = false;
                    saveData();
                    System.out.println("感谢使用，再见！");
                    break;
                default:
                    System.out.println("无效输入，请重新选择。");
            }

            if (loggedInUser != null) {
                showMainMenu();
            }
        }
        scanner.close();
    }

    // 注册新用户
    private static void register()  {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        if (findUser(username) != null) {
            System.out.println("用户名已存在，请选择其他用户名。");
        } else {
            users.add(new User(username, password));
            System.out.println("注册成功，请登录！");
        }
    }

    // 登录
    private static void login() {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        User user = findUser(username);
        if (user != null && user.password.equals(password)) {
            loggedInUser = user;
            System.out.println("登录成功，欢迎 " + username + "！");
        } else {
            System.out.println("用户名或密码错误，请重试。");
        }
    }

    private static User findUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    // 主菜单
    private static void showMainMenu() {
        boolean inForum = true;
        while (inForum) {
            System.out.println("\n=== 论坛主菜单 ===");
            System.out.println("1. 发帖");
            System.out.println("2. 查看帖子");
            System.out.println("3. 查看话题");
            System.out.println("4. 创建话题");
            System.out.println("5. 退出登录");
            System.out.print("请选择操作（输入数字）：");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createPost();
                    break;
                case "2":
                    viewPosts();
                    break;
                case "3":
                    viewTopics();
                    break;
                case "4":
                    createTopic();
                    break;
                case "5":
                    loggedInUser = null;
                    inForum = false;
                    System.out.println("您已退出登录。");
                    break;
                default:
                    System.out.println("无效输入，请重新选择。");
            }
        }
    }

    // 创建话题
    private static void createTopic() {
        System.out.print("请输入新话题的名称：");
        String topicName = scanner.nextLine();

        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName)) {
                System.out.println("话题已存在，请选择其他名称。");
                return;
            }
        }

        Topic newTopic = new Topic(topicName);
        topics.add(newTopic);
        System.out.println("话题创建成功！");
        saveData();
    }

    // 创建新帖子
    private static void createPost() {
        System.out.println("请选择话题：");
        for (int i = 0; i < topics.size(); i++) {
            System.out.println((i + 1) + ". " + topics.get(i).getName());
        }
        int topicChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (topicChoice < 0 || topicChoice >= topics.size()) {
            System.out.println("无效的话题选择！");
            return;
        }

        System.out.print("请输入帖子标题：");
        String title = scanner.nextLine();
        System.out.print("请输入帖子内容：");
        String content = scanner.nextLine();

        Post post = new Post(loggedInUser.username, title, content, topics.get(topicChoice));
        posts.add(post);
        topics.get(topicChoice).addPost(post);

        System.out.println("帖子发布成功！");
        saveData();
    }

    // 查看所有帖子
    private static void viewPosts() {
        if (posts.isEmpty()) {
            System.out.println("当前没有帖子。");
            return;
        }

        for (int i = 0; i < posts.size(); i++) {
            System.out.println((i + 1) + ". " + posts.get(i).getTitle() + " - " + posts.get(i).getAuthor());
        }

        System.out.print("请输入要查看的帖子序号：");
        int postChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (postChoice < 0 || postChoice >= posts.size()) {
            System.out.println("无效的帖子序号！");
            return;
        }

        Post selectedPost = posts.get(postChoice);
        System.out.println("标题: " + selectedPost.getTitle());
        System.out.println("作者: " + selectedPost.getAuthor());
        System.out.println("内容: " + selectedPost.getContent());

        System.out.println("\n评论：");
        for (Comment comment : selectedPost.getComments()) {
            System.out.println(comment.getAuthor() + ": " + comment.getContent());
        }

        System.out.print("是否要发表评论？(Y/N): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            addComment(selectedPost);
        }
    }

    // 增加评论
    private static void addComment(Post post) {
        System.out.print("请输入评论内容：");
        String content = scanner.nextLine();

        Comment comment = new Comment(loggedInUser.username, content);
        post.addComment(comment);

        System.out.println("评论添加成功！");
        saveData();
    }

    // 查看话题
    private static void viewTopics() {
        if (topics.isEmpty()) {
            System.out.println("当前没有话题。");
            return;
        }

        System.out.println("=== 话题列表 ===");
        for (int i = 0; i < topics.size(); i++) {
            System.out.println((i + 1) + ". " + topics.get(i).getName());
        }

        System.out.print("请选择要查看的话题序号：");
        int topicChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (topicChoice < 0 || topicChoice >= topics.size()) {
            System.out.println("无效的选择！");
            return;
        }

        Topic selectedTopic = topics.get(topicChoice);
        System.out.println("=== 话题: " + selectedTopic.getName() + " ===");

        ArrayList<Post> postsInTopic = selectedTopic.getPosts();
        if (postsInTopic.isEmpty()) {
            System.out.println("该话题下没有帖子。");
        } else {
            for (int i = 0; i < postsInTopic.size(); i++) {
                System.out.println((i + 1) + ". 标题: " + postsInTopic.get(i).getTitle() + " | 作者: " + postsInTopic.get(i).getAuthor());
            }
        }
    }


    // 保存数据
    private static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("forum_data.txt"))) {
            out.writeObject(users);
            out.writeObject(posts);
            out.writeObject(topics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 加载数据
    private static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("forum_data.txt"))) {
            users = (ArrayList<User>) in.readObject();
            posts = (ArrayList<Post>) in.readObject();
            topics = (ArrayList<Topic>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("首次运行，创建新数据文件...");
        }
    }
}


// 用户类
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

// 帖子类
class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    private String author;
    private String title;
    private String content;
    private Topic topic;
    private ArrayList<Comment> comments;

    public Post(String author, String title, String content, Topic topic) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.comments = new ArrayList<>();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Topic getTopic() {
        return topic;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}

// 评论类
class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String author;
    private String content;

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}

// 话题类
class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Post> posts;

    public Topic(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}

