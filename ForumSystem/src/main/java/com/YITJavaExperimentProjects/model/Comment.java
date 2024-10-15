package com.YITJavaExperimentProjects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int commentId;
    private String content;
    private int userId;
    private int postId;
    private Date creationDate;

    public Comment(String content, int userId, int postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }
}
