package com.bjb.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    private Integer sort;
    private String IP;
    private String port;
    private String dbName;
    private String title;
    private String userName;
    private String password;
}
