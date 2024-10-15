package com.YITJavaExperimentProjects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    private int topicId;
    private String topicName;
    private String description;

    public Topic(String topicName, String description) {
        this.topicName = topicName;
        this.description = description;
    }
}
