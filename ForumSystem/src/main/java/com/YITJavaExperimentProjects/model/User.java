package com.YITJavaExperimentProjects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int userId;
    private String userName;
    private String email;
    private String passwd;
    private Date registrationDate;
    private Date lastLoginDate;

    public User(String userName, String email, String passwd) {
        this.userName = userName;
        this.email = email;
        this.passwd = passwd;
    }
}
