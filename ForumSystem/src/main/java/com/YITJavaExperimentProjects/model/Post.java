package com.YITJavaExperimentProjects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int postId;
    private String title;
    private String content;
    private int userId;
    private int topicId;
    private Date creationDate;

    public Post(String title, String content, int userId, int topicId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.topicId = topicId;
    }
}
