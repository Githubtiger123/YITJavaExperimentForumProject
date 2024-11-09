package YITJavaExperimentProjects.dao;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ForumAppGUI {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Post> posts = new ArrayList<>();
    static ArrayList<Topic> topics = new ArrayList<>();
    static User loggedInUser = null;

    private JFrame mainFrame;
    private JPanel contentPanel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadData();
        ForumAppGUI app = new ForumAppGUI();
        app.createMainFrame();
    }

    private void createMainFrame() {
        mainFrame = new JFrame("简易论坛");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        showLoginScreen();

        mainFrame.setVisible(true);
    }

    private void showRegisterScreen() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton submitButton = new JButton("提交");
        JButton backButton = new JButton("返回");

        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        registerPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(backButton, gbc);

        mainFrame.setContentPane(registerPanel);

        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (findUser(username) != null) {
                JOptionPane.showMessageDialog(mainFrame, "用户名已存在，请选择其他用户名。");
            } else {
                users.add(new User(username, password));
                JOptionPane.showMessageDialog(mainFrame, "注册成功，请登录！");
                showLoginScreen();
            }
        });

        backButton.addActionListener(e -> showLoginScreen());

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showLoginScreen() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 添加大字标题
        JLabel titleLabel = new JLabel("    论坛系统");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        JButton exitButton = new JButton("退出");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        loginPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        loginPanel.add(exitButton, gbc);

        mainFrame.setContentPane(loginPanel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = findUser(username);
            if (user != null && user.password.equals(password)) {
                loggedInUser = user;
                JOptionPane.showMessageDialog(mainFrame, "登录成功，欢迎 " + username + "！");
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "用户名或密码错误，请重试。");
            }
        });

        registerButton.addActionListener(e -> showRegisterScreen());
        exitButton.addActionListener(e -> {
            saveData();
            System.exit(0);
        });

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showMainMenu() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton viewPostsButton = new JButton("查看帖子");
        JButton createPostButton = new JButton("发帖");
        JButton viewTopicsButton = new JButton("查看话题");
        JButton createTopicButton = new JButton("创建话题");
        JButton logoutButton = new JButton("退出登录");

        leftPanel.add(viewPostsButton);
        leftPanel.add(createPostButton);
        leftPanel.add(viewTopicsButton);
        leftPanel.add(createTopicButton);
        leftPanel.add(logoutButton);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JLabel("欢迎 " + loggedInUser.getUsername() + "！"), BorderLayout.NORTH);

        mainMenuPanel.add(leftPanel, BorderLayout.WEST);
        mainMenuPanel.add(contentPanel, BorderLayout.CENTER);

        mainFrame.setContentPane(mainMenuPanel);

        viewPostsButton.addActionListener(e -> showPosts());
        createPostButton.addActionListener(e -> createPost());
        viewTopicsButton.addActionListener(e -> showTopics());
        createTopicButton.addActionListener(e -> createTopic());
        logoutButton.addActionListener(e -> {
            loggedInUser = null;
            showLoginScreen();
        });

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showPosts() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        if (posts.isEmpty()) {
            contentPanel.add(new JLabel("当前没有帖子。"), BorderLayout.CENTER);
        } else {
            JPanel postListPanel = new JPanel();
            postListPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

            for (int i = 0; i < posts.size(); i++) {
                Post post = posts.get(i);
                JButton postButton = new JButton(post.getTitle() + " - " + post.getAuthor());
                postButton.addActionListener(e -> showPostDetails(post));
                postButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // 固定高度为50像素

                gbc.gridy = i;
                postListPanel.add(postButton, gbc);
            }

            // 将 postListPanel 放入 JScrollPane 中
            JScrollPane scrollPane = new JScrollPane(postListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // 将 JScrollPane 添加到 contentPanel 中
            contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showPostDetails(Post post) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        String comments = post.getComments().stream()
                .map(comment -> comment.getAuthor() + ": " + comment.getContent())
                .reduce("", (acc, comment) -> acc + comment + "\n");

        String message = String.format("标题: %s\n作者: %s\n内容: %s\n评论:\n%s",
                post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                comments.isEmpty() ? "无评论" : comments);

        JTextArea postDetailsArea = new JTextArea(message);
        postDetailsArea.setEditable(false);
        contentPanel.add(new JScrollPane(postDetailsArea), BorderLayout.CENTER);

        JButton addCommentButton = new JButton("添加评论");
        addCommentButton.addActionListener(e -> addComment(post));
        contentPanel.add(addCommentButton, BorderLayout.SOUTH);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void createPost() {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] topicNames = topics.stream().map(Topic::getName).toArray(String[]::new);
        JComboBox<String> topicComboBox = new JComboBox<>(topicNames);
        JTextField titleField = new JTextField(20);
        JTextArea contentArea = new JTextArea(5, 20);
        contentArea.setLineWrap(true);

        JButton confirmButton = new JButton("确认");
        JButton cancelButton = new JButton("取消");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("选择话题："), gbc);
        gbc.gridx = 1;
        contentPanel.add(topicComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("标题："), gbc);
        gbc.gridx = 1;
        contentPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("内容："), gbc);
        gbc.gridx = 1;
        contentPanel.add(new JScrollPane(contentArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPanel.add(confirmButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(cancelButton, gbc);

        confirmButton.addActionListener(e -> {
            String topicName = (String) topicComboBox.getSelectedItem();
            String title = titleField.getText();
            String content = contentArea.getText();

            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "标题和内容不能为空！");
                return;
            }

            Topic selectedTopic = topics.stream().filter(t -> t.getName().equals(topicName)).findFirst().orElse(null);
            Post post = new Post(loggedInUser.username, title, content, selectedTopic);
            posts.add(post);
            if (selectedTopic != null) {
                selectedTopic.addPost(post);
            }

            JOptionPane.showMessageDialog(mainFrame, "帖子发布成功！");
            saveData();
            showPosts();
        });

        cancelButton.addActionListener(e -> showPosts());

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addComment(Post post) {
        String content = JOptionPane.showInputDialog(mainFrame, "请输入评论内容：");
        Comment comment = new Comment(loggedInUser.username, content);
        post.addComment(comment);

        JOptionPane.showMessageDialog(mainFrame, "评论添加成功！");
        saveData();
        showPostDetails(post);
    }

    private void showTopics() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        if (topics.isEmpty()) {
            contentPanel.add(new JLabel("当前没有话题。"), BorderLayout.CENTER);
        } else {
            JPanel topicListPanel = new JPanel();
            topicListPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

            for (int i = 0; i < topics.size(); i++) {
                Topic topic = topics.get(i);
                JButton topicButton = new JButton(topic.getName());
                topicButton.addActionListener(e -> showTopicPosts(topic));
                topicButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // 固定高度为50像素

                gbc.gridy = i;
                topicListPanel.add(topicButton, gbc);
            }

            // 将 topicListPanel 放入 JScrollPane 中
            JScrollPane scrollPane = new JScrollPane(topicListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // 将 JScrollPane 添加到 contentPanel 中
            contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showTopicPosts(Topic topic) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        if (topic.getPosts().isEmpty()) {
            contentPanel.add(new JLabel("该话题下没有帖子。"), BorderLayout.CENTER);
        } else {
            JPanel postListPanel = new JPanel();
            postListPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

            for (int i = 0; i < topic.getPosts().size(); i++) {
                Post post = topic.getPosts().get(i);
                JButton postButton = new JButton(post.getTitle() + " - " + post.getAuthor());
                postButton.addActionListener(e -> showPostDetails(post));
                postButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // 固定高度为50像素

                gbc.gridy = i;
                postListPanel.add(postButton, gbc);
            }

            // 将 postListPanel 放入 JScrollPane 中
            JScrollPane scrollPane = new JScrollPane(postListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // 将 JScrollPane 添加到 contentPanel 中
            contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
    }


    private void createTopic() {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField topicNameField = new JTextField(20);
        JButton confirmButton = new JButton("确认");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("请输入新话题的名称："), gbc);
        gbc.gridx = 1;
        contentPanel.add(topicNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(confirmButton, gbc);

        confirmButton.addActionListener(e -> {
            String topicName = topicNameField.getText();
            if (topicName != null && !topicName.trim().isEmpty()) {
                topics.add(new Topic(topicName));
                JOptionPane.showMessageDialog(mainFrame, "新话题创建成功！");
                saveData();
                showTopics();
            }
        });

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private User findUser(String username) {
        return users.stream().filter(u -> u.username.equals(username)).findFirst().orElse(null);
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