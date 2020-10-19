package com.bjb.project.entity;

import lombok.Data;

@Data
public class Database {
    private Integer sort;
    private String IP;
    private String port;
    private String dbName;
    private String title;
    private String userName;
    private String password;
}
