package com.CST;


import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton viewPostsButton = new JButton("查看帖子");
        JButton createPostButton = new JButton("发帖");
        JButton viewTopicsButton = new JButton("查看话题");
        JButton createTopicButton = new JButton("创建话题");
        JButton logoutButton = new JButton("退出登录");
        JButton searchButton = new JButton("搜索"); // 添加搜索按钮
        JButton viewFavoritesButton = new JButton("查看收藏"); // 添加查看收藏按钮

        // 添加按钮到 leftPanel，并在按钮之间添加垂直间距
        leftPanel.add(viewPostsButton);
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        leftPanel.add(createPostButton);
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        leftPanel.add(viewTopicsButton);
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        leftPanel.add(createTopicButton);
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        leftPanel.add(searchButton); // 添加搜索按钮
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
        leftPanel.add(viewFavoritesButton); // 添加查看收藏按钮
        leftPanel.add(Box.createVerticalStrut(10)); // 添加垂直间距
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
        searchButton.addActionListener(e -> showSearchScreen()); // 添加搜索按钮的监听器
        viewFavoritesButton.addActionListener(e -> showFavorites()); // 添加查看收藏按钮的监听器
        logoutButton.addActionListener(e -> {
            loggedInUser = null;
            showLoginScreen();
        });

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showFavorites() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        if (loggedInUser.getFavorites().isEmpty()) {
            contentPanel.add(new JLabel("当前没有收藏的帖子。"), BorderLayout.CENTER);
        } else {
            JPanel favoriteListPanel = new JPanel();
            favoriteListPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

            for (int i = 0; i < loggedInUser.getFavorites().size(); i++) {
                Post post = loggedInUser.getFavorites().get(i);
                JButton postButton = new JButton(post.getTitle() + " - " + post.getAuthor());
                postButton.addActionListener(e -> showPostDetails(post));
                postButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // 固定高度为50像素

                gbc.gridy = i;
                favoriteListPanel.add(postButton, gbc);
            }

            // 将 favoriteListPanel 放入 JScrollPane 中
            JScrollPane scrollPane = new JScrollPane(favoriteListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // 将 JScrollPane 添加到 contentPanel 中
            contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
    }
    private void showSearchScreen() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JPanel searchBarPanel = new JPanel();
        searchBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");

        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        searchPanel.add(searchBarPanel, BorderLayout.NORTH);

        JPanel searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText();
            ArrayList<Post> searchResults = searchPosts(keyword);
            searchResultsPanel.removeAll();

            if (searchResults.isEmpty()) {
                searchResultsPanel.add(new JLabel("没有找到匹配的帖子。"), gbc);
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    Post post = searchResults.get(i);
                    JButton postButton = new JButton(post.getTitle() + " - " + post.getAuthor());
                    postButton.addActionListener(e1 -> showPostDetails(post));
                    postButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // 固定高度为50像素

                    gbc.gridy = i;
                    searchResultsPanel.add(postButton, gbc);
                }
            }

            searchResultsPanel.revalidate();
            searchResultsPanel.repaint();
        });

        JScrollPane scrollPane = new JScrollPane(searchResultsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(searchPanel, BorderLayout.CENTER);

        mainFrame.revalidate();
        mainFrame.repaint();
    }


    private ArrayList<Post> searchPosts(String keyword) {
        ArrayList<Post> searchResults = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    post.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(post);
            }
        }
        return searchResults;
    }


    private void showPosts() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        if (posts.isEmpty()) {
            contentPanel.add(new JLabel("当前没有帖子。"), BorderLayout.CENTER);
        } else {
            // 对帖子列表进行排序，置顶的帖子放在前面
            posts.sort((p1, p2) -> {
                if (p1.isPinned() && !p2.isPinned()) {
                    return -1;
                } else if (!p1.isPinned() && p2.isPinned()) {
                    return 1;
                } else {
                    return 0;
                }
            });

            JPanel postListPanel = new JPanel();
            postListPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5); // 添加间距

            for (int i = 0; i < posts.size(); i++) {
                Post post = posts.get(i);
                JButton postButton = new JButton(post.getTitle() + " - " + post.getAuthor() + (post.isPinned() ? " (置顶)" : ""));
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

        // 创建一个面板来显示帖子详情
        JPanel postDetailsPanel = new JPanel();
        postDetailsPanel.setLayout(new BoxLayout(postDetailsPanel, BoxLayout.Y_AXIS));
        postDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 帖子标题
        JLabel titleLabel = new JLabel("标题: " + post.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        postDetailsPanel.add(titleLabel);

        // 帖子作者
        JLabel authorLabel = new JLabel("作者: " + post.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        postDetailsPanel.add(authorLabel);

        // 分隔线
        postDetailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // 帖子内容
        JTextPane contentPane = new JTextPane();
        contentPane.setContentType("text/html"); // 支持富文本格式
        contentPane.setText("<html><body style='font-family: Serif; font-size: 16px;'>" + post.getContent() + "</body></html>");
        contentPane.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentPane);
        contentScrollPane.setPreferredSize(new Dimension(600, 200));
        postDetailsPanel.add(contentScrollPane);

        // 分隔线
        postDetailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // 评论部分
        JLabel commentsLabel = new JLabel("评论:");
        commentsLabel.setFont(new Font("Serif", Font.BOLD, 18));
        commentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        postDetailsPanel.add(commentsLabel);

        JTextPane commentsPane = new JTextPane();
        commentsPane.setContentType("text/html"); // 支持富文本格式
        commentsPane.setText("<html><body style='font-family: Serif; font-size: 16px;'>" +
                post.getComments().stream()
                        .map(comment -> comment.getAuthor() + ": " + comment.getContent())
                        .reduce("", (acc, comment) -> acc + comment + "<br>") +
                "</body></html>");
        commentsPane.setEditable(false);
        JScrollPane commentsScrollPane = new JScrollPane(commentsPane);
        commentsScrollPane.setPreferredSize(new Dimension(600, 200));
        postDetailsPanel.add(commentsScrollPane);

        // 添加评论部分
        JPanel commentInputPanel = new JPanel();
        commentInputPanel.setLayout(new BorderLayout());

        JTextField commentField = new JTextField(30);
        JButton addCommentButton = new JButton("添加评论");

        commentInputPanel.add(commentField, BorderLayout.CENTER);
        commentInputPanel.add(addCommentButton, BorderLayout.EAST);

        postDetailsPanel.add(commentInputPanel);

        addCommentButton.addActionListener(e -> {
            String content = commentField.getText();
            if (content.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "评论内容不能为空！");
            } else {
                Comment comment = new Comment(loggedInUser.username, content);
                post.addComment(comment);
                JOptionPane.showMessageDialog(mainFrame, "评论添加成功！");
                saveData();
                showPostDetails(post); // 重新加载帖子详情
            }
        });

        // 添加收藏按钮
        JButton favoriteButton = new JButton("收藏");
        favoriteButton.addActionListener(e -> {
            loggedInUser.addFavorite(post);
            JOptionPane.showMessageDialog(mainFrame, "帖子已收藏！");
            saveData();
        });
        postDetailsPanel.add(favoriteButton);

        // 添加置顶按钮
        JButton pinButton = new JButton(post.isPinned() ? "取消置顶" : "置顶");
        pinButton.addActionListener(e -> {
            post.setPinned(!post.isPinned());
            JOptionPane.showMessageDialog(mainFrame, post.isPinned() ? "帖子已置顶！" : "帖子已取消置顶！");
            saveData();
            showPosts(); // 重新加载帖子列表
        });
        postDetailsPanel.add(pinButton);

        // 添加返回按钮
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel backLabel = new JLabel(new ImageIcon("back_arrow.png")); // 替换为实际的返回箭头图标路径
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(backLabel, BorderLayout.EAST);
        topPanel.add(new JLabel(""), BorderLayout.CENTER); // 占位符

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPosts(); // 返回上一层
            }
        });

        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(postDetailsPanel, BorderLayout.CENTER);

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
    ArrayList<Post> favorites; // 添加收藏列表

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.favorites = new ArrayList<>(); // 确保 favorites 被初始化
    }

    public String getUsername() {
        return username;
    }

    public void addFavorite(Post post) {
        favorites.add(post);
    }

    public ArrayList<Post> getFavorites() {
        return favorites;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
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
    private boolean isPinned; // 新增置顶属性

    public Post(String author, String title, String content, Topic topic) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.comments = new ArrayList<>();
        this.isPinned = false; // 默认不置顶
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

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
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